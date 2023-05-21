import React from "react";
import CommentButton from "./CommentButton";

const CommentForm = ({ newComment, handleCommentSubmit, setNewComment }) => {
    return (
        <form className="comment-form" onSubmit={handleCommentSubmit}>
            <input
                type="text"
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
                placeholder="Add a comment..."
            />
            <button type="submit">Submit</button>
        </form>
    );
};
export default CommentForm;
