import React from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn } from 'mdb-react-ui-kit';
import Navbar from "../components/Navbar";
import defaultAvatar from "../pictures/default-avatar.jpg";
import { Card, Button, Badge } from 'react-bootstrap';
import '../css/UserProfile.css';
import {useParams} from "react-router";

export default function UserProfile() {
    const { userId } = useParams();
    const [userData, setUserData] = React.useState(null);

    React.useEffect(() => {
        // Fetch user profile data
        const fetchUserProfile = async () => {
            try {
                const response = await axios.get('http://localhost:8080/profile/' + userId);
                const data = response.data;
                console.log(data);
                setUserData(data);
            } catch (error) {
                console.error('Error fetching user profile:', error);
            }
        };
        fetchUserProfile();
        // Simulating initial data for each role
        const initialData = {
            userId: '123',
            fullName: 'John Doe',
            profileDescription: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
            userAvatar: 'https://example.com/avatar.jpg',
            phoneNumber: '123-456-7890',
            gender: 'Male',
            birthdate: '1990-01-01',
            address: '123 Main St',
            companyName: 'Example Company',
            companyType: 'Technology',
            economicScale: 5,
            Certificate_Skills: ['Skill 1', 'Skill 2', 'Skill 3'],
            workExperience: ['Company A', 'Company B'],
            recruiting_startDate: '2022-01-01',
            rank: 'Senior',
            last_year_score: 8.5,
            roles: ['careerExpert'],
            followers: 100,
            numberOfConnections: 10,
            numberOfFollowers: 11,
            numberOfEmployees: 9,
        };

        setUserData(initialData);
    }, []);

    const handleConnect = () => {
        // axios.post('/connections', { userId: userData.userId })
        //   .then(response => {
        //     console.log('Connection request sent');
        //     // Handle success
        //   })
        //   .catch(error => {
        //     console.error('Error sending connection request:', error);
        //     // Handle error
        //   });
    };

    const handleFollow = () => {
        // Send follow request logic
        console.log('Follow request sent');
        // Make an axios request to send the follow request
        // Example:
        // axios.post('/follow', { userId: userData.userId })
        //   .then(response => {
        //     console.log('Follow request sent');
        //     // Handle success
        //   })
        //   .catch(error => {
        //     console.error('Error sending follow request:', error);
        //     // Handle error
        //   });
    };
    const handleChat = () => {
        // Send follow request logic
        console.log('Follow request sent');
        // Make an axios request to send the follow request
        // Example:
        // axios.post('/follow', { userId: userData.userId })
        //   .then(response => {
        //     console.log('Follow request sent');
        //     // Handle success
        //   })
        //   .catch(error => {
        //     console.error('Error sending follow request:', error);
        //     // Handle error
        //   });
    };
    if (!userData) {
        return <div>Loading...</div>;
    }

    const {
        fullName,
        username,
        profileDescription,
        userAvatar,
        phoneNumber,
        gender,
        birthdate,
        address,
        companyName,
        companyType,
        economicScale,
        Certificate_Skills,
        workExperience,
        recruiting_startDate,
        rank,
        last_year_score,
        roles,
        followers,
        allUsers,
        numberOfConnections,
        numberOfFollowers,
        numberOfEmployees,

    } = userData;

    const avatar = defaultAvatar;



    return (
        <div>
            <Navbar />
            <MDBContainer className="mt-5">
                <MDBRow className="justify-content-center">
                    <MDBCol md="9" lg="7" xl="5" className="d-flex custom-col-width">
                        <MDBCard className="w-100">
                            <MDBCardBody className="p-4">
                                <div className="d-flex text-black">
                                    <div className="flex-shrink-0" style={{ width: '180px' }}>
                                        <MDBCardImage
                                            style={{ borderRadius: '10px' }}
                                            src={avatar}
                                            alt="User Avatar"
                                            fluid
                                        />
                                        <div className="post-meta">
                                            <div className="meta-item">
                                                <Badge variant="info" className="follower-count">
                                                    {numberOfFollowers} {numberOfFollowers === 1 ? 'Follower' : 'Followers'}
                                                </Badge>
                                                <Badge variant="info" className="connect-count">
                                                    {numberOfConnections} {numberOfConnections === 1 ? 'Connect' : 'Connects'}
                                                </Badge>
                                                <Badge variant="info" className="employee-count">
                                                    {numberOfEmployees} {numberOfEmployees === 1 ? 'Employee' : 'Employees'}
                                                </Badge>
                                            </div>
                                        </div>
                                        <div className="d-flex pt-1">
                                            <MDBBtn outline className="me-1 flex-grow-1" onClick={handleChat}>
                                                Chat
                                            </MDBBtn>
                                            {!roles.includes('company') && (
                                                <MDBBtn className="flex-grow-1" onClick={handleConnect}>
                                                    Connect
                                                </MDBBtn>
                                            )}
                                            <MDBBtn className="flex-grow-1" onClick={handleFollow}>
                                                Follow
                                            </MDBBtn>
                                        </div>
                                    </div>
                                    <div className="flex-grow-1 ms-3" style={{ maxWidth: '1000px' }}>
                                        <MDBCardTitle>{fullName}</MDBCardTitle>
                                        <MDBCardText>{profileDescription}</MDBCardText>

                                        {roles.includes('company') ? (
                                            <div className="company-details d-flex justify-content-start rounded-3 p-2 mb-2" style={{ backgroundColor: '#efefef' }}>
                                                <div className="px-3">
                                                    <p className="small text-muted mb-1">Company Type</p>
                                                    <p className="mb-0">{companyType}</p>
                                                </div>
                                                <div>
                                                    <p className="small text-muted mb-1">Economic Scale</p>
                                                    <p className="mb-0">{economicScale}</p>
                                                </div>
                                            </div>
                                        ) : (
                                            <div className="d-flex justify-content-start rounded-3 p-2 mb-2" style={{ backgroundColor: '#efefef' }}>
                                                <div className="px-3">
                                                    <p className="small text-muted mb-1">Phone Number</p>
                                                    <p className="mb-0">{phoneNumber}</p>
                                                </div>
                                                <div>
                                                    <p className="small text-muted mb-1">Gender</p>
                                                    <p className="mb-0">{gender}</p>
                                                </div>
                                                <div className="px-3">
                                                    <p className="small text-muted mb-1">Birthdate</p>
                                                    <p className="mb-0">{birthdate}</p>
                                                </div>
                                            </div>
                                        )}


                                    </div>
                                </div>

                                {/* Regular User Details */}
                                {roles.includes('regularUser') && (
                                    <MDBCard className="mt-3">
                                        <MDBCardBody>
                                            <h5>Certificate/Skills:</h5>
                                            <ul>
                                                {Certificate_Skills.map((item, index) => (
                                                    <li key={index}>{item}</li>
                                                ))}
                                            </ul>
                                            <h5>Work Experience:</h5>
                                            <ul>
                                                {workExperience.map((item, index) => (
                                                    <li key={index}>{item}</li>
                                                ))}
                                            </ul>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}

                                {/* Recruiter Details */}
                                {roles.includes('recruiter') && (
                                    <MDBCard className="mt-3">
                                        <MDBCardBody>
                                            <p>Recruiting Start Date: {recruiting_startDate}</p>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}

                                {/* Career Expert Details */}
                                {roles.includes('careerExpert') && (
                                    <MDBCard className="mt-3">
                                        <MDBCardBody>
                                            <p>Rank: {rank}</p>
                                            <p>Last Year's Score: {last_year_score}</p>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}
                            </MDBCardBody>
                        </MDBCard>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
        </div>
    );
}
