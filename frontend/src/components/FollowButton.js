import { Button } from "react-bootstrap";
import React from "react";

const FollowButton = ({ followedByUser, handleFollow }) => {
    return (
        <Button
            variant={followedByUser ? "primary" : "outline-primary"}
            className={`follow-button${followedByUser ? " followed" : ""}`}
            onClick={handleFollow}
        >
            {followedByUser ? "Followed" : "Follow"}
        </Button>
    );
};

export default FollowButton;
