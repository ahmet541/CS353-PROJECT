import React, {useEffect, useState} from 'react';
import { Card, Button, Badge } from 'react-bootstrap';
import defaultAvatar from '../pictures/default-avatar.jpg';
import '../css/Post.css';
import AuthorLink from "./AuthorLink";
import CommentButton from "./CommentButton";
import LikeButton from "./LikeButton";
import CommentForm from "./CommentForm";
import axios from "axios";


const Post = ({ heading, content, authorId, postId, sharedTime }) => {
    const [showComments, setShowComments] = useState(false);
    const [comments, setComments] = useState(null);
    const [newComment, setNewComment] = useState('');
    const [likes, setLikes] = useState(0);
    const [likedByUser, setLikedByUser] = useState(false); // State for tracking if the current user has liked the post

    useEffect(() => {
        // Fetch initial post data (comments, likes, and likedByUser) from the backend
        const fetchPostData = async () => {

            try {
                const response = await axios.get(`http://localhost:8080/post/${postId}/getPostExtraInfo/${sessionStorage.getItem('userId')}`);

                // Destructure the data from the response
                const { comments, likes, likedByUser } = response.data;

                // Update the state with the fetched data
                setComments(comments);
                setLikes(likes);
                setLikedByUser(likedByUser);



            } catch (error) {
                // Handle any error that occurs during the request
                console.log("Error message: " + error.response.data);
            }
        };

        fetchPostData();
    }, [postId]);


    const handleToggleComments = async () => {
        setShowComments((prevShowComments) => !prevShowComments);

        if (!showComments) {
            try {
                // Make a request to the backend to retrieve the comments
                const response = await axios.get(`http://localhost:8080/post/${postId}/comments`);
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
            console.log(newComment);
            await axios.post(`http://localhost:8080/post/${postId}/addComment/${sessionStorage.getItem('userId')}`, {
                comment: newComment,
            });

            // After the comment is successfully added, retrieve the updated comments
            const response = await axios.get(`http://localhost:8080/post/${postId}/comments`);
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
            let response;
            if (likedByUser) {
                // If the post is already liked, send a request to unlike it
                response = await axios.delete(`http://localhost:8080/post/${postId}/unlike/${sessionStorage.getItem('userId')}`);
            } else {
                // If the post is not liked, send a request to like it
                response = await axios.post(`http://localhost:8080/post/${postId}/like/${sessionStorage.getItem('userId')}`);
            }

            // After the request is successful, retrieve the updated post information
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
                    <AuthorLink authorId={authorId} />
                </div>
                <div className="post-meta">
                    <span className="shared-time"> {new Date(sharedTime).toLocaleString('en-US', {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit'
                    })}</span>
                    <Badge variant="info" className="post-tag">
                        Informative
                    </Badge>
                </div>
                <Card.Title>{heading}</Card.Title>
                <Card.Text>{content}</Card.Text>
                <div className="post-meta">
                    <div className="meta-item">
                        <Badge variant="info" className="comment-count">
                            {comments ? comments.length : 0} {comments && comments.length === 1 ? 'Comment' : 'Comments'}
                        </Badge>
                        <Badge variant="info" className="like-count">
                            {likes} {likes === 1 ? 'Like' : 'Likes'}
                        </Badge>
                    </div>
                </div>
                <div className="post-actions">
                    <CommentButton commentsCount={comments ? comments.length : 0} handleToggleComments={handleToggleComments} />
                    <LikeButton likedByUser={likedByUser} handleLike={handleLike} />
                </div>

                {showComments && (
                    <div className="comments-section">
                        <h6>Comments:</h6>
                        {comments.length > 0 ? (
                            <ul className="comment-list">
                                {comments.map((comment) => (
                                    <li key={comment.commentId} className="comment-item">
                                        <div className="author-info">
                                            <AuthorLink authorId={comment.userId} />
                                            <p className="comment-date comment-date-small">
                                                {new Date(comment.date).toLocaleString('en-US', {
                                                    year: 'numeric',
                                                    month: 'short',
                                                    day: 'numeric',
                                                    hour: 'numeric',
                                                    minute: 'numeric',
                                                })}
                                            </p>
                                        </div>
                                        <div className="comment-content">
                                            <p>{comment.context.replace(/"/g, '')}</p> {/* Remove double quotation marks */}
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p>No comments yet.</p>
                        )}

                        <CommentForm newComment={newComment} handleCommentSubmit={handleCommentSubmit} setNewComment={setNewComment} />
                    </div>
                )}
            </Card.Body>
        </Card>
    );
};

export default Post;
