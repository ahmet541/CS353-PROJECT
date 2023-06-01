import { Button } from "react-bootstrap";
import React from "react";
import '../css/UserProfile.css';

const ConnectButton = ({ pending, connectedByUser, handleConnect }) => {
    let variant = connectedByUser ? "primary" : "outline-primary";
    let buttonText = connectedByUser ? "Connected" : "Connect";

    if (pending) {
        variant = "secondary"; // Change the variant to grey (secondary)
        buttonText = "Pending";
    }
    else if( connectedByUser){
        variant = "secondary";
        buttonText = "Connected"
    }
    else{
        variant = "primary";
        buttonText = "Connect"
    }
    return (
        <Button
            variant={connectedByUser ? "primary" : pending ? "secondary" : "outline-primary"}
            className={`connect-button${connectedByUser ? " connected" : ""}`}
            onClick={handleConnect}
        >
            {pending ? "Pending" : connectedByUser ? "Connected" : "Connect"}
        </Button>
    );
};

export default ConnectButton;
