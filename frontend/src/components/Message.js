import React, { useState } from 'react';
import { Container, Form, Button, Card } from 'react-bootstrap';
import '../css/Message.css';

const Message = ({ selectedUserId }) => {
    const [messages, setMessages] = useState([
        { content: 'Hello', senderId: 3, timestamp: '2023-06-01 10:30' },
        { content: 'Hi there!', senderId: selectedUserId, timestamp: '2023-06-01 10:31' },
        { content: 'How are you?', senderId: 3, timestamp: '2023-06-01 10:32' },
        { content: 'I\'m doing great!', senderId: selectedUserId, timestamp: '2023-06-01 10:33' },
    ]);

    const [newMessage, setNewMessage] = useState('');

    const handleSendMessage = () => {
        if (newMessage.trim() !== '') {
            const timestamp = new Date().toISOString().slice(0, 16).replace('T', ' ');
            const updatedMessages = [
                ...messages,
                { content: newMessage, senderId: selectedUserId, timestamp },
            ];
            setMessages(updatedMessages);
            setNewMessage('');
        }
    };
console.log()
    return (
        <Card className="mb-4 post">
            <Card.Body>
                <h1>Messaging App with userId : {selectedUserId}</h1>
                <div className="message-list">
                    {messages.map((message, index) => (
                        <div key={message.timestamp} className={`message ${message.senderId === parseInt(sessionStorage.getItem("userId"),10) ? 'own-message' : 'recipient-message'}`}>
                            <p>{message.content}</p>
                            <div className="post-meta">
                                <span className="shared-time">{message.timestamp}</span>
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
