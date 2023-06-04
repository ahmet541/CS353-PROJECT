import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import '../css/HomeScreen.css';
import Post from '../components/Post';
import axios from 'axios';
import {useNavigate} from "react-router-dom";
import UserRole from "../Enum/UserRole";
import PostType from "../Enum/PostType";

const HomeScreen = () => {
    const navigate = useNavigate();
    const [posts, setPosts] = useState([]);
    const [newPostHeading, setNewPostHeading] = useState('');
    const [newPostContent, setNewPostContent] = useState('');
    const [newPostType, setNewPostType] = useState(PostType.COMMENT);
    const [errorMessage, setErrorMessage] = useState('');
    const [userId, setUserId] = useState(sessionStorage.getItem("userId"));
    const [userRole, setUserRole] = useState(sessionStorage.getItem("userRole"));

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await axios.get('http://localhost:8080/post/getAllPostOfConnectionsAndFollows/' + sessionStorage.getItem('userId'));
                console.log(response);
                setPosts(response.data);
            } catch (error) {
                console.log(error);
                setErrorMessage('Something went wrong. Try to refresh page.\n');
            }
        };

        fetchPosts();
    }, []);

    const handlePostCreate = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/post/' + sessionStorage.getItem('userId'), {
                heading: newPostHeading,
                explanation: newPostContent,
                type: newPostType,
            });

            setPosts((prevPosts) => [ response.data, ...prevPosts]);

            setNewPostHeading('');
            setNewPostContent('');
            setNewPostType(PostType.COMMENT)
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };


    function handleCreateJobAdvert() {
        navigate('/createadvert');
    }

    return (
        <div>
            <Navbar/>
            <div className="post-container">
                <h2>Welcome to the Home Page!</h2>
                {/*UserRole.RECRUITER || userRole == UserRole.CAREER_EXPERT*/}
                    <div className="create-post">
                        <h3>Create a New Post</h3>
                        <form onSubmit={handlePostCreate}>
                            <div className="form-group">
                                <label htmlFor="userType">Post Type:</label>
                                <select id="userType" value={newPostType} onChange={(e) => setNewPostType(e.target.value)} className="form-input">
                                    <option value={PostType.COMMENT}>{PostType.COMMENT}</option>
                                    {userRole === UserRole.CAREER_EXPERT && (
                                        <option value={PostType.INFORMATIVE}>{PostType.INFORMATIVE}</option>
                                    )}
                                </select>
                            </div>

                            <label>Title:</label>
                            <div className="form-group">
                                <textarea
                                    type="text"
                                    className="form-control custom-input no-resize"
                                    style={{ width: "600px", height: "60px" }}
                                    value={newPostHeading}
                                    onChange={(e) => setNewPostHeading(e.target.value)}
                                />
                            </div>
                            <label>Content:</label>
                            <div className="form-group">
                                <textarea
                                    className="form-control custom-input no-resize"
                                    style={{ width: "600px", height: "125px" }}
                                    value={newPostContent}
                                    onChange={(e) => setNewPostContent(e.target.value)}
                                />
                            </div>
                            <button type="submit" className="btn btn-primary">Create Post</button>
                        </form>
                    </div>

                <h3>Posts</h3>
                {errorMessage && <p>{errorMessage}</p>}
                <div className="posts-wrapper">
                    {posts.map((post) => (
                        <div key={post.postId} className="post-item">
                            <Post
                                heading={post.heading}
                                content={post.explanation}
                                postType={post.type}
                                sharedTime={post.date}
                                authorId={post.userId}
                                postId={post.postId}
                            />
                        </div>
                    ))}
                </div>
                {userRole === "RECRUITER" && (
                    <button
                        style={{position: "fixed",
                            bottom: "20px",
                            right: "20px"}}
                        className="btn btn-primary create-job-advert-button"
                        onClick={handleCreateJobAdvert}
                    >
                        Create Job Advert
                    </button>
                )}
            </div>
        </div>
    );
};

export default HomeScreen;
