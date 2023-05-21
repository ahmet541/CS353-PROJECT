import defaultAvatar from "../pictures/default-avatar.jpg";
import React from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "./Navbar";

const AuthorLink = ({ name, avatar, authorId }) => {
    const navigate = useNavigate();
    const avatarSource = avatar || defaultAvatar;

    const handleAuthorClick = () => {
        navigate(`/profile/${authorId}`);
    };

    return (
        <div className="author-link">
            <img src={avatarSource} alt="Author Avatar" className="author-avatar" />
            <span className="author-name" onClick={handleAuthorClick} style={{ cursor: 'pointer', textDecoration: 'underline' }}>{name}</span>
        </div>
    );
};

export default AuthorLink;
