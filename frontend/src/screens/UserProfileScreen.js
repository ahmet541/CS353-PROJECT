import React from 'react';
import axios from 'axios';
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardImage, MDBCardBody, MDBCardTitle, MDBCardText, MDBBtn } from 'mdb-react-ui-kit';
import Navbar from "../components/Navbar";
import defaultAvatar from "../pictures/default-avatar.jpg";
import { Card, Button, Badge } from 'react-bootstrap';
import '../css/UserProfile.css';
import {useParams} from "react-router";
import FollowButton from "../components/FollowButton";
import LikeButton from "../components/LikeButton";
import * as PropTypes from "prop-types";
import ConnectButton from "../components/ConnectButton";
import UserRole from "../Enum/UserRole";
import { useNavigate, Link } from 'react-router-dom';
import RegisterScreen from "./RegisterScreen";


// ConnectButton.propTypes = {
//     handleConnect: PropTypes.func,
//     connectedByUser: PropTypes.any
// };
const UserProfileScreen = () => {
    const navigate = useNavigate();
    const { userId } = useParams();
    const [userData, setUserData] = React.useState(null);
    const [conReqPending, setConReqPending] = React.useState(false);
    // const [followers, setFollower] = React.useState([]);
    // const [connections, setConnections] = React.useState([]);
    // const [employees, setEmployees] = React.useState([]);
    React.useEffect(() => {
        // Fetch user profile data
        const fetchUserProfile = async () => {
            try {
                const response = await axios.get('http://localhost:8080/profile/' + userId);
                const data = response.data;
                console.log(data);
                setUserData(data);

                const isPending = await axios.get(`http://localhost:8080/connect/${sessionStorage.getItem('userId')}/isPending/${userId}`);
                setConReqPending(isPending.data);

            } catch (error) {
                console.error('Error fetching user profile:', error);
            }
        };
        fetchUserProfile();
        // Simulating initial data for each role

    }, []);

    const handleConnect = async () => {
        try {
            let response;
            const isConnected = connections.some(connection => connection.userId === parseInt(sessionStorage.getItem('userId')));

            if (isConnected || conReqPending) {
                // If the post is already liked, send a request to unlike it
                response = await axios.delete(`http://localhost:8080/connect/${sessionStorage.getItem('userId')}/removeConnection/${userId}`);
            } else {
                // If the post is not liked, send a request to like it
                response = await axios.post(`http://localhost:8080/connect/${sessionStorage.getItem('userId')}/sendRequest/${userId}`);
            }

            // After the request is successful, retrieve the updated post information

            // Update the likes and likedByUser state with the new values
            if (response && response.data) {
                setUserData((prevState) => ({ ...prevState, connections: response.data }));
            }

            const isPending = await axios.get(`http://localhost:8080/connect/${sessionStorage.getItem('userId')}/isPending/${userId}`);
            setConReqPending(isPending.data);
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };

    const handleFollow = async () => {
        try {
            let response;
            const isFollowed = followers.some(follower => follower.userId === parseInt(sessionStorage.getItem('userId'), 10))
            if (isFollowed) {
                // If the post is already liked, send a request to unlike it
                response = await axios.delete(`http://localhost:8080/follow/${sessionStorage.getItem('userId')}/${userId}`);

            } else {
                // If the post is not liked, send a request to like it
                response = await axios.post(`http://localhost:8080/follow/${sessionStorage.getItem('userId')}/${userId}`);
            }
            console.log(response);
            // After the request is successful, retrieve the updated post information

            // Update the likes and likedByUser state with the new values
            if (response && response.data) {
                setUserData((prevState) => ({ ...prevState, followers: response.data }));
            }
        } catch (error) {
            // Handle any error that occurs during the request
            // const errorMessage = error.response.data;
        }
    };

    const handleChat = () => {
        sessionStorage.setItem("selectedUserId", userId);
        sessionStorage.setItem("selectedUserName", fullName);

        navigate("/messages");
    };
    const handleEditProfile = () => {
        navigate('/editProfile');
    };
    const handleVerify = () => {
        axios.post(`http://localhost:8080/profile/${sessionStorage.getItem("userId")}/verifyAsRecruiter/${userId}`)
            .then((response) => {
                console.log(response);
            })
            .catch((error)=> {
                console.log(error);
            })
    };
    if (!userData) {
        return <div>Loading...</div>;
    }

    const {
        fullName,
        profileDescription,
        userAvatar,
        phoneNumber,
        gender,
        birthdate,
        address,
        companyType,
        economicScale,
        Certificate_Skills,
        workExperience,
        recruiting_startDate,
        rank,
        last_year_score,
        roles,
        followers,
        connections,
        employees,

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
                                    <div className="flex-shrink-0" style={{ width: '300px' }}>
                                        <MDBCardImage
                                            style={{ borderRadius: '10px' }}
                                            src={avatar}
                                            alt="User Avatar"
                                            fluid
                                        />

                                        <div className="d-flex pt-1">
                                            {sessionStorage.getItem("userId") === userId && (
                                                <>
                                                    <button onClick={handleEditProfile} className="me-1 flex-grow-1">
                                                        Edit Profile
                                                    </button>
                                                    {/* ... */}
                                                </>
                                            )}
                                        </div>

                                        <div className="post-meta">
                                            <div className="meta-item">
                                                <Badge variant="info" className="follower-count">
                                                    {followers.length} {followers.length === 1 ? 'Follower' : 'Followers'}
                                                </Badge>
                                                <Badge variant="info" className="connect-count">
                                                    {connections.length} {connections.length === 1 ? 'Connection' : 'Connections'}
                                                </Badge>
                                                {roles.includes(UserRole.COMPANY) && (
                                                    <Badge variant="info" className="employee-count">
                                                        {employees.length} {employees.length === 1 ? 'Employee' : 'Employees'}
                                                    </Badge>)}
                                            </div>
                                        </div>
                                        {sessionStorage.getItem("userId") !== userId &&
                                            <div className="d-flex pt-1">
                                                <MDBBtn outline onClick={handleChat}>
                                                    Chat
                                                </MDBBtn>
                                                {!roles.includes(UserRole.COMPANY) && (
                                                    <ConnectButton pending={conReqPending}
                                                                   connectedByUser={connections.some(connection => connection.userId === parseInt(sessionStorage.getItem('userId')))}
                                                                   handleConnect={handleConnect}/>
                                                )}
                                                <FollowButton
                                                    followedByUser={followers.some(follower => follower.userId === parseInt(sessionStorage.getItem('userId')))}
                                                    handleFollow={handleFollow}/>
                                                {sessionStorage.getItem("userRole") === UserRole.COMPANY && roles.includes(UserRole.REGULAR_USER) && (
                                                    <MDBBtn outline onClick={handleVerify}>
                                                        Verify
                                                    </MDBBtn>
                                                )}
                                            </div>
                                        }
                                    </div>
                                    <div className="flex-shrink-1 ms-3" style={{ maxWidth: '1000px' }}>
                                        <MDBCardTitle>{fullName}</MDBCardTitle>
                                        <MDBCardText>{profileDescription}</MDBCardText>

                                        {roles.includes(UserRole.COMPANY) ? (
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
                                                <div className="px-3">
                                                    <p className="small text-muted mb-1">Address</p>
                                                    <p className="mb-0">{address}</p>
                                                </div>
                                            </div>
                                        )}


                                    </div>
                                </div>

                                {/* Regular User Details */}
                                {roles.includes(UserRole.REGULAR_USER) && (
                                    <MDBCard className="mt-3">
                                        <MDBCardBody>
                                            <h5>Certificate/Skills:</h5>
                                            <ul>
                                                {Certificate_Skills && Certificate_Skills.map((item, index) => (
                                                    <li key={index}>{item}</li>
                                                ))}
                                            </ul>
                                            <h5>Work Experience:</h5>
                                            <ul>
                                                {workExperience && workExperience.map((item, index) => (
                                                    <li key={index}>{item}</li>
                                                ))}
                                            </ul>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}

                                {/* Recruiter Details */}
                                {roles.includes(UserRole.RECRUITER) && (
                                    <MDBCard className="mt-3">
                                        <MDBCardBody>
                                            <p>Recruiting Start Date: {recruiting_startDate}</p>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}

                                {/* Career Expert Details */}
                                {roles.includes(UserRole.RECRUITER) && (
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
export default UserProfileScreen;

