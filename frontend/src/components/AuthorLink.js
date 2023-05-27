import defaultAvatar from "../pictures/default-avatar.jpg";
import React, {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";
import axios from "axios";

const AuthorLink = ({ authorId }) => {
    const [author, setAuthor] = useState({ userId: "", fullName: "", avatar: "" });

    const navigate = useNavigate();
    const avatarSource = defaultAvatar; // avatar ||    add this if we add avatar

    useEffect(() => {
        // Fetch initial post data (comments, likes, and likedByUser) from the backend
        const fetchPostData = async () => {

            try {
                const authorResponse = await axios.get(`http://localhost:8080/user/${authorId}/getOwner`);
                setAuthor(authorResponse.data);

            } catch (error) {
                // Handle any error that occurs during the request
                console.log("Error message: " + error.response.data);
            }
        };

        fetchPostData();
    }, [authorId]);

    const handleAuthorClick = () => {
        navigate(`/profile/${author.userId}`);
    };


    return (
        <div className="author-link">
            <a href={`/profile/${author.userId}`}>
                <img src={avatarSource} alt="Author Avatar" className="author-avatar" />
            </a>
            <span className="author-name" onClick={handleAuthorClick} style={{ cursor: 'pointer', textDecoration: 'underline' }}>
                {author.fullName}
            </span>
        </div>
    );
};

export default AuthorLink;
