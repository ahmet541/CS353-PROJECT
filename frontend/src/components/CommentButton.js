import {Button} from "react-bootstrap";
import React from "react";
import AuthorLink from "./AuthorLink";

const CommentButton = ({ commentsCount, handleToggleComments }) => {
    return (
        <Button variant="primary" className="comment-button" onClick={handleToggleComments}>
            Comment {commentsCount}
        </Button>
    );
};
export default CommentButton;
