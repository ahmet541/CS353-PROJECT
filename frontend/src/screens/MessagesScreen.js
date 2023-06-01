import React, { useEffect, useState } from "react";
import {
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBCard,
    MDBCardBody,
    MDBTypography,
    MDBBtn, MDBModal, MDBModalHeader, MDBModalBody, MDBModalFooter,
} from "mdb-react-ui-kit";
import defaultAvatar from "../pictures/default-avatar.jpg";
import Message from "../components/Message";
import { useParams } from "react-router";
import ChatConnections from "../components/ChatConnections";
import axios from "axios";
import Navbar from "../components/Navbar";
const MessagesScreen = () => {
    const [selectedIndex, setSelectedIndex] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [chats, setChats] = useState([]);
    const [connectionData, setConnectionData] = useState([]);

    // const [chats, setChats] = useState([
    //     { userId: 1, username: "John Doe", message: "Hello, Are you there?", time: "Just now" },
    //     { userId: 2, username: "Danny Smith", message: "Lorem ipsum dolor sit.", time: "5 mins ago" },
    //     { userId: 3, username: "Alex Steward", message: "Lorem ipsum dolor sit.", time: "Yesterday" },
    //     { userId: 4, username: "Ashley Olsen", message: "Lorem ipsum dolor sit.", time: "Yesterday" },
    //     { userId: 5, username: "Kate Moss", message: "Lorem ipsum dolor sit.", time: "Yesterday" },
    //     { userId: 6, username: "Lara Croft", message: "Lorem ipsum dolor sit.", time: "Yesterday" },
    //     { userId: 7, username: "Brad Pitt", message: "Lorem ipsum dolor sit.", time: "5 mins ago" },
    // ]);
    // const connectionData = [
    //     { id: 1, name: "John Doe" },
    //     { id: 2, name: "Jane Smith" },
    //     { id: 3, name: "David Johnson" },
    //     { id: 55, name: "Sarah Williams" },
    //     // Add more connections as needed
    // ];
    useEffect(() => {
        const fetchChatsAndConnections = async () => {
            try {
                // Fetch chats from the backend
                const chatsResponse = await axios.get("http://localhost:8080/message/" + sessionStorage.getItem("userId")+ "/chats");
                setChats(chatsResponse.data);

                // Fetch connection data from the backend
                const connectionsResponse = await axios.get("http://localhost:8080/connect/" + sessionStorage.getItem("userId") + "/allConnections");
                setConnectionData(connectionsResponse.data);


                // Perform the remaining operations that depend on setChats completion
                const userId = sessionStorage.getItem('selectedUserId') ? parseInt(sessionStorage.getItem('selectedUserId'), 10) : -1;
                const userName = sessionStorage.getItem("selectedUserName");
                sessionStorage.removeItem("selectedUserId");
                sessionStorage.removeItem("selectedUserName");

                setChats((prevChats) => {
                    const userIndex = prevChats.findIndex((chat) => chat.userId === userId);

                    if (userIndex !== -1) {
                        setSelectedIndex(userIndex);
                    } else if (userId > -1) {
                        const newChat = {
                            userId,
                            fullName: userName,
                            message: "",
                            time: "Just now"
                        };
                        const updatedChats = [newChat, ...prevChats];
                        setSelectedIndex(0); // Set the selected index to the newly created chat
                        return updatedChats;
                    }

                    return prevChats;
                });
            } catch (error) {
                console.log(error);
            }
        };

        fetchChatsAndConnections();
    }, []);


    const handleUserSelect = (userId) => {
        const userIndex = chats.findIndex((chat) => chat.userId === userId);
        setSelectedIndex(userIndex);
    };
    const handleCreateChat = () => {

        // Perform the logic to create a chat with your connections
        // For example, you can open a modal or navigate to a new page for selecting connections to chat with
        setShowModal(true);

    };
    const handleConnectionSelect = (userId, name) => {
        const userIndex = chats.findIndex((chat) => chat.userId === userId);

        if (userIndex !== -1) {
            setSelectedIndex(userIndex);
        } else {
            // Logic for creating a new chat with the selected connection
            const newChat = {
                userId,
                fullName: name  ,
                message: "",
                time: "Just now",
            };
            setChats((prevChats) => [newChat, ...prevChats]);
            setSelectedIndex(0); // Set the selected index to the newly created chat
        }

        setShowModal(false);
    };
    return (
        <Navbar>
        <MDBContainer fluid className="py-5" style={{ backgroundColor: "#fcfcfc" }}>
            <MDBRow>
                <MDBCol md="6" lg="5" xl="4" className="mb-4 mb-md-0">
                    <MDBBtn color="primary" onClick={handleCreateChat} className="mb-4">
                        Create Chat with Connections
                    </MDBBtn>
                    <ChatConnections
                        showModal={showModal}
                        setShowModal={setShowModal}
                        connectionData={connectionData}
                        handleConnectionSelect={handleConnectionSelect}
                    />

                    <MDBCard>
                        <MDBCardBody>
                            <MDBTypography listUnStyled className="mb-0">
                                {chats.map((chat, index) => (
                                    <li
                                        key={chat.userId}
                                        className={`p-2 border-bottom ${
                                            selectedIndex === index ? "bg-primary text-white" : ""
                                        }`}
                                        onClick={() => handleUserSelect(chat.userId, index)}
                                        style={{ cursor: "pointer" }}
                                    >
                                        <div className="d-flex flex-row">
                                            <img
                                                src={defaultAvatar}
                                                alt="avatar"
                                                className="rounded-circle d-flex align-self-center me-3 shadow-1-strong"
                                                width="60"
                                            />
                                            <div className="pt-1">
                                                <p className={`fw-bold mb-0 ${selectedIndex === index ? "text-white" : ""}`}>
                                                    {chat.fullName}
                                                </p>
                                                <p className={`small ${selectedIndex === index ? "text-white" : "text-muted"}`}>
                                                    {chat.message}
                                                </p>
                                            </div>
                                        </div>
                                    </li>
                                ))}
                            </MDBTypography>
                        </MDBCardBody>
                    </MDBCard>
                </MDBCol>

                <MDBCol md="6" lg="7" xl="8">
                    {selectedIndex !== null && (
                        <MDBTypography listUnStyled>
                            {/* Placeholder for displaying selected user's chat */}
                            <Message selectedUserId={chats[selectedIndex].userId} selectedUserName={chats[selectedIndex].fullName} />
                        </MDBTypography>
                    )}
                </MDBCol>
            </MDBRow>

        </MDBContainer>
        </Navbar>
    );
};

export default MessagesScreen;
