import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import UserRole from '../Enum/UserRole';

const EditProfileScreen = () => {
    const navigate = useNavigate();
    const [userId, setUserId] = useState(sessionStorage.getItem("userId"));
    const [formData, setFormData] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');
    const [errors, setErrors] = useState({});
    React.useEffect(() => {
        // Fetch user profile data
        const fetchEditProfile = async () => {
            try {
                const response = await axios.get('http://localhost:8080/profile/' + sessionStorage.getItem("userId") + '/getEditForm');
                const data = response.data;
                console.log(data);
                setFormData(data);

            } catch (error) {
                console.error('Error fetching user profile:', error);
            }
        };
        fetchEditProfile();
        // Simulating initial data for each role

    }, []);


    const handleSubmit = async (e) => {
        console.log(e);
        console.log(formData);
        e.preventDefault();

        try {
            // Make API call to update the profile with formData
            const response = await axios.put('http://localhost:8080/profile/' + sessionStorage.getItem("userId") + '/update', formData);
            console.log(response.data);
            navigate('/profile/' + sessionStorage.getItem("userId"));
        } catch (error) {
            console.log(error);
            setErrorMessage('Profile update failed. Please try again.');
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const {
        companyName = '',
        firstName = '',
        lastName = '',
        profileDescription,
        userAvatar,
        phoneNumber,
        gender,
        birthdate,
        address,
        companyType,
        economicScale,
        Certificate_Skills = {},
        roles,
    } = formData || {};


    if (!formData) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>Edit Profile</h2>
            {errorMessage && <p>{errorMessage}</p>}
            <form onSubmit={handleSubmit}>
                {roles.includes(UserRole.COMPANY) && (
                    <div>
                        <div>
                            <label htmlFor="companyName">Company Name:</label>
                            <input
                                type="text"
                                id="companyName"
                                name="companyName"
                                value={companyName}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="companyType">Company Type:</label>
                            <input
                                type="text"
                                id="companyType"
                                name="companyType"
                                value={companyType || ""}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="economicScale">Economic Scale:</label>
                            <input
                                type="text"
                                id="economicScale"
                                name="economicScale"
                                value={economicScale || ""}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                )}
                {roles.includes(UserRole.REGULAR_USER || UserRole.RECRUITER || UserRole.CAREER_EXPERT) && (
                    <div>
                        <div>
                            <label htmlFor="fullName">First Name:</label>
                            <input
                                type="text"
                                id="firstName"
                                name="firstName"
                                value={firstName}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="lastName">Last Name:</label>
                            <input
                                type="text"
                                id="lastName"
                                name="lastName"
                                value={lastName}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="phoneNumber">Phone Number:</label>
                            <input
                                type="text"
                                id="phoneNumber"
                                name="phoneNumber"
                                value={phoneNumber}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="gender">Gender:</label>
                            <select
                                id="gender"
                                name="gender"
                                value={gender}
                                onChange={handleChange}
                            >
                                <option value=""></option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        <div>
                            <label htmlFor="birthdate">Birthdate:</label>
                            <input
                                type="date"
                                id="birthdate"
                                name="birthdate"
                                value={birthdate}
                                onChange={handleChange}
                            />
                        </div>
                        <div>
                            <label htmlFor="address">Address:</label>
                            <textarea
                                id="address"
                                name="address"
                                value={address}
                                onChange={handleChange}
                            />
                        </div>
                    </div>
                )}
                <div>
                    <label htmlFor="profileDescription">Profile Description:</label>
                    <textarea
                        id="profileDescription"
                        name="profileDescription"
                        value={profileDescription}
                        onChange={handleChange}
                    />
                </div>

                {/* Add other fields here */}
                <button type="submit">Save</button>
            </form>
        </div>
    );
};


export default EditProfileScreen;
