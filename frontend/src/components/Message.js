import React, {useEffect, useState} from 'react';
import { Container, Form, Button, Card } from 'react-bootstrap';
import '../css/Message.css';
import axios from "axios";

const Message = ({ selectedUserId, selectedUserName}) => {
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState('');

    useEffect(() => {
        // Fetch messages from the server
        axios.get(`http://localhost:8080/message/${sessionStorage.getItem("userId")}/getAll/${selectedUserId}`)
            .then(response => {
                setMessages(response.data);
            })
            .catch(error => {
                console.error('Error fetching messages:', error);
            });
    }, [selectedUserId]);

    const handleSendMessage = () => {
        if (newMessage.trim() !== '') {

            // Send new message to the server
            axios.post('http://localhost:8080/message/create', {
                content: newMessage,
                senderId: parseInt(sessionStorage.getItem("userId"),10),
                receiverId: selectedUserId,
            })
                .then(response => {
                    // Update the messages state with the new message
                    setMessages(prevMessages => [...prevMessages, response.data]);
                    setNewMessage('');
                })
                .catch(error => {
                    console.error('Error sending message:', error);
                });
        }
    };

    return (
        <Card className="mb-4 post">
            <Card.Body>
                <h1>Messaging App with {selectedUserName}</h1>
                <div className="message-list">
                    {messages.map((message, index) => (
                        <div key={message.postDate} className={`message ${message.senderId === parseInt(sessionStorage.getItem("userId"),10) ? 'own-message' : 'recipient-message'}`}>
                            <p>{message.content}</p>
                            <div className="post-meta">
                            <span className="shared-time">
                              {new Date(message.postDate).toLocaleString('en-US', {
                                  year: 'numeric',
                                  month: '2-digit',
                                  day: '2-digit',
                                  hour: '2-digit',
                                  minute: '2-digit'
                              })}
                            </span>
                            </div>
                        </div>
                    ))}
                </div>
                <br/>
                <div className="message-input">
                    <Form.Control
                        type="text"
                        value={newMessage}
                        onChange={(e) => setNewMessage(e.target.value)}
                    />
                    <Button onClick={handleSendMessage}>Send</Button>
                </div>
            </Card.Body>
        </Card>
    );
};

export default Message;
