import React, { useState, useEffect } from 'react';
import Navbar from '../components/Navbar';
import '../css/HomeScreen.css';
import Post from '../components/Post';
import axios from 'axios';

const HomeScreen = () => {
    const [posts, setPosts] = useState([]);
    const [newPostTitle, setNewPostTitle] = useState('');
    const [newPostContent, setNewPostContent] = useState('');

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await axios.get('/api/posts');
                setPosts(response.data);
            } catch (error) {
                // Handle any error that occurs during the request
                // const errorMessage = error.response.data;
            }
        };

        fetchPosts();
    }, []);

    const handlePostCreate = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('/api/posts', {
                title: newPostTitle,
                content: newPostContent,
            });

            setPosts((prevPosts) => [...prevPosts, response.data]);

            setNewPostTitle('');
            setNewPostContent('');
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };

    useEffect(() => {
        const currentDate = new Date();

        setPosts([
            {
                postId: '1',
                title: 'Post 1',
                content: 'This is the content of Post 111\n11111111111111111111111111111111111111111111111',
                author: {
                    name: 'John Doe',
                    authorId: 'author1',
                },
            },
            {
                postId: '2',
                title: 'Post 2',
                sharedTime: currentDate.toLocaleString(),
                content: 'This is the content of Post 2',
                author: {
                    name: 'Jane Smith',
                    authorId: 'author2',
                },
            },
        ]);
    }, []);

    return (
        <div>
            <Navbar />
            <div className="post-container">
                <h2>Welcome to the Home Page!</h2>
                <p>This is the home page of your application.</p>
                <div className="create-post">
                    <h3>Create a New Post</h3>
                    <form onSubmit={handlePostCreate}>
                        <label>Title:</label>
                        <div className="form-group">
                            <textarea
                                type="text"
                                className="form-control custom-input no-resize"
                                style={{ width: "600px", height: "60px" }}
                                value={newPostTitle}
                                onChange={(e) => setNewPostTitle(e.target.value)}
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
                <div className="posts-wrapper">
                    {posts.map((post) => (
                        <div key={post.postId} className="post-item">
                            <Post
                                title={post.title}
                                content={post.content}
                                sharedTime={post.sharedTime}
                                author={{
                                    name: post.author.name,
                                    authorId: post.author.authorId,
                                }}
                                postId={post.postId}
                            />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default HomeScreen;
