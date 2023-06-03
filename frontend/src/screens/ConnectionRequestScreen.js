import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {  MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn } from 'mdb-react-ui-kit';
import Navbar from "../components/Navbar";
import defaultAvatar from "../pictures/default-avatar.jpg";
import '../css/ConnectionRequest.css'
import {Button} from "react-bootstrap";
const ConnectionRequestsScreen = () => {
    const [requests, setRequests] = useState([]);
    const fetchConnectionRequests = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/connect/${sessionStorage.getItem("userId")}/requests`);
            setRequests(response.data);
            console.log(response);
        } catch (error) {
            console.log(error);
        }
    };
    useEffect(() => {
        fetchConnectionRequests();
    }, []);

    const acceptRequest = async (senderId, receiverId) => {
        try {
            await axios.post(`http://localhost:8080/connect/${senderId}/acceptRequest/${receiverId}`);
            console.log('Connection request accepted!');
            fetchConnectionRequests();
        } catch (error) {
            console.log(error);
        }
    };

    const rejectRequest = async (senderId, receiverId) => {
        try {
            await axios.delete(`http://localhost:8080/connect/${senderId}/removeConnection/${receiverId}`);
            console.log('Connection request rejected!');
            fetchConnectionRequests();
        } catch (error) {
            console.log(error);
        }
    };


    return (
        <div>
            <Navbar />
            {(requests.length == 0 && <h1> No requests</h1>)}
            <MDBContainer className="py-5">
                <MDBRow>
                    {requests.map((request) => (
                        <MDBCol key={request.userId} md="4" className="mb-4">
                            <MDBCard>
                                <MDBCardBody>
                                    <MDBCardImage
                                        src={defaultAvatar}
                                        alt={request.fullName}
                                        top="true" // Pass "true" as a string
                                        hover="true" // Pass "true" as a string
                                        overlay="white-slight"
                                        style={{ maxWidth: '50px', maxHeight: '50px' }}
                                    />

                                    <MDBCardTitle>{request.fullName}</MDBCardTitle>
                                    <MDBCardText>{/* Display additional information here */}</MDBCardText>
                                    <div className="d-grid gap-2">
                                        <Button
                                            className="btn btn-success custom-btn"
                                            onClick={() => acceptRequest(request.userId, sessionStorage.getItem("userId"))}
                                        >
                                            Accept
                                        </Button>

                                        <Button
                                            className="btn btn-danger custom-btn"
                                            onClick={() => rejectRequest(request.userId, sessionStorage.getItem("userId"))}
                                        >
                                            Reject
                                        </Button>


                                    </div>
                                </MDBCardBody>
                            </MDBCard>
                        </MDBCol>
                    ))}
                </MDBRow>
            </MDBContainer>
        </div>
    );
};

export default ConnectionRequestsScreen;
