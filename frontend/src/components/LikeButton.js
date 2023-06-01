import { Button } from "react-bootstrap";
import React from "react";
import '../css/UserProfile.css';

const LikeButton = ({ likedByUser, handleLike }) => {
    return (
        <Button
            variant={likedByUser ? "primary" : "outline-primary"}
            className={`like-button${likedByUser ? " liked" : ""}`}
            onClick={handleLike}
        >
            {likedByUser ? "Liked" : "Like"}
        </Button>
    );
};

export default LikeButton;
