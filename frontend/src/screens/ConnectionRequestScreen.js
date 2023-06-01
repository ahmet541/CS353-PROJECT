import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn } from 'mdb-react-ui-kit';
import Navbar from "../components/Navbar";

const ConnectionRequestsScreen = () => {
    const [requests, setRequests] = useState([ {
        id: 1,
        name: 'John Doe',
        image: 'https://example.com/johndoe.jpg',
        message: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
    },
        {
            id: 2,
            name: 'Jane Smith',
            image: 'https://example.com/janesmith.jpg',
            message: 'Pellentesque nec urna id arcu tempus vehicula.',
        },
        // Add more connection requests as needed
    ]);

    useEffect(() => {
        const fetchConnectionRequests = async () => {
            try {
                const response = await axios.get('http://localhost:8080/connection-requests');
                setRequests(response.data);
            } catch (error) {
                console.log(error);
            }
        };

        fetchConnectionRequests();
    }, []);

    const acceptRequest = async (requestId) => {
        try {
            await axios.post(`http://localhost:8080/connection-requests/${requestId}/accept`);
            // If needed, you can perform additional actions after accepting the request
            // For example, updating the UI or displaying a success message
            console.log('Connection request accepted!');
        } catch (error) {
            console.log(error);
        }
    };

    const rejectRequest = async (requestId) => {
        try {
            await axios.post(`http://localhost:8080/connection-requests/${requestId}/reject`);
            // If needed, you can perform additional actions after rejecting the request
            // For example, updating the UI or displaying a success message
            console.log('Connection request rejected!');
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div>
            <Navbar />
        <MDBContainer className="py-5">
            <MDBRow>
                {requests.map((request) => (
                    <MDBCol key={request.id} md="4" className="mb-4">
                        <MDBCard>
                            <MDBCardImage src={request.profileImage} alt={request.name} top hover overlay="white-slight" />
                            <MDBCardBody>
                                <MDBCardTitle>{request.name}</MDBCardTitle>
                                <MDBCardText>{request.message}</MDBCardText>
                                <div className="d-grid gap-2">
                                    <MDBBtn color="success" onClick={() => acceptRequest(request.id)}>Accept</MDBBtn>
                                    <MDBBtn color="danger" onClick={() => rejectRequest(request.id)}>Reject</MDBBtn>
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
