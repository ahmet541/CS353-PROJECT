import React, {useEffect, useState} from 'react';
import { Card, Button, Badge } from 'react-bootstrap';
import defaultAvatar from '../pictures/default-avatar.jpg';
import '../css/Post.css';
import AuthorLink from "./AuthorLink";
import CommentButton from "./CommentButton";
import LikeButton from "./LikeButton";
import CommentForm from "./CommentForm";
import axios from "axios";


const Post = ({ title, content, author, postId, sharedTime }) => {
    const [showComments, setShowComments] = useState(false);
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');
    const [likes, setLikes] = useState(0);
    const [likedByUser, setLikedByUser] = useState(false); // State for tracking if the current user has liked the post

    useEffect(() => {
        // Fetch initial post data (comments, likes, and likedByUser) from the backend
        const fetchPostData = async () => {
            try {
                const response = await axios.get(`/api/posts/${postId}`);

                // Destructure the data from the response
                const { comments, likes, likedByUser } = response.data;

                // Update the state with the fetched data
                setComments(comments);
                setLikes(likes);
                setLikedByUser(likedByUser);
            } catch (error) {
                // Handle any error that occurs during the request
                // const errorMessage = error.response.data;
            }
        };

        fetchPostData();
    }, [postId]);


    const handleToggleComments = async () => {
        setShowComments((prevShowComments) => !prevShowComments);

        if (!showComments) {
            try {
                // Make a request to the backend to retrieve the comments
                const response = await axios.get(`/api/posts/${postId}/comments`);
                const fetchedComments = response.data;

                // Update the comments state with the fetched comments
                setComments(fetchedComments);
            } catch (error) {
                // Handle any error that occurs during the request
                // const errorMessage = error.response.data;
            }
        }
    };


    const handleCommentSubmit = async (e) => {
        e.preventDefault();

        try {
            // Make a request to the backend to add the comment
            await axios.post(`/api/posts/${postId}/comments`, {
                comment: newComment,
            });

            // After the comment is successfully added, retrieve the updated comments
            const response = await axios.get(`/api/posts/${postId}/comments`);
            const updatedComments = response.data;

            // Update the comments state with the new comments
            setComments(updatedComments);
            setNewComment('');
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };


    const handleLike = async () => {
        try {
            if (likedByUser) {
                // If the post is already liked, send a request to unlike it
                await axios.delete(`/api/posts/${postId}/like`);
            } else {
                // If the post is not liked, send a request to like it
                await axios.post(`/api/posts/${postId}/like`);
            }

            // After the request is successful, retrieve the updated post information
            const response = await axios.get(`/api/posts/${postId}`);
            const { likes: updatedLikes, likedByUser: updatedLikedByUser } = response.data;

            // Update the likes and likedByUser state with the new values
            setLikes(updatedLikes);
            setLikedByUser(updatedLikedByUser);
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };


    return (
        <Card className="mb-4 post">
            <Card.Body>
                <div className="author-info">
                    <AuthorLink name={author.name} avatar={author.avatar} authorId={author.authorId} />
                </div>
                <div className="post-meta">
                    <span className="shared-time">{sharedTime}</span>
                </div>
                <Card.Title>{title}</Card.Title>
                <Card.Text>{content}</Card.Text>
                <div className="post-meta">
                    <div className="meta-item">
                        <Badge variant="info" className="comment-count">
                            {comments.length} {comments.length === 1 ? 'Comment' : 'Comments'}
                        </Badge>
                        <Badge variant="info" className="like-count">
                            {likes} {likes === 1 ? 'Like' : 'Likes'}
                        </Badge>
                    </div>
                </div>
                <div className="post-actions">
                    <CommentButton commentsCount={comments.length} handleToggleComments={handleToggleComments} />
                    <LikeButton likedByUser={likedByUser} handleLike={handleLike} />
                </div>

                {showComments && (
                    <div className="comments-section">
                        <h6>Comments:</h6>
                        {comments.length > 0 ? (
                            <ul className="comment-list">
                                {comments.map((comment, index) => (
                                    <li key={index} className="comment-item">
                                        {comment}
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p>No comments yet.</p>
                        )}
                        <CommentForm
                            newComment={newComment}
                            handleCommentSubmit={handleCommentSubmit}
                            setNewComment={setNewComment}
                        />
                    </div>
                )}
            </Card.Body>
        </Card>
    );
};

export default Post;
