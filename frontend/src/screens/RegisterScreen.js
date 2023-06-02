import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import UserRole from "../Enum/UserRole";

const RegisterScreen = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState(UserRole.REGULAR_USER);
    const [companyName, setCompanyName] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [gender, setGender] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [address, setAddress] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [errors, setErrors] = useState([]);

    const validateFields = async (requestData) => {
        let updatedErrors = [];

        if (!requestData.email) {
            updatedErrors = { ...updatedErrors, email: 'Please enter an email' };
        }
        if (!requestData.password) {
            updatedErrors = { ...updatedErrors, password: 'Please enter a password' };
        }
        if (!userType) {
            updatedErrors = { ...updatedErrors, userType: 'Please select a user type' };
        }
        if (userType === UserRole.COMPANY) {
            if (!requestData.companyName) {
                updatedErrors = { ...updatedErrors, companyName: 'Company Name is required' };
            }
        } else if (userType === UserRole.REGULAR_USER) {
            if (!requestData.firstName) {
                updatedErrors = { ...updatedErrors, firstName: 'First name is required for Regular User' };
            }
        }

        setErrors(updatedErrors);
        return updatedErrors;
    };



    const handleRegister = async () => {

        let response;
        try {
            let requestData = {
                email: email,
                password: password,
            };
            if (userType === UserRole.COMPANY) {
                requestData.companyName = companyName;
            }
            else if( userType === UserRole.REGULAR_USER){
                requestData.firstName = firstName;
                requestData.lastName = lastName;
                requestData.gender = gender;
                requestData.phoneNumber = phoneNumber;
                requestData.birthdate = birthdate;
                requestData.address = address;
            }

            let updatedErrors = await validateFields(requestData);

            if (Object.keys(updatedErrors).length > 0) {
                setErrorMessage('Please fill in all required fields');
                return;
            }
            console.log(requestData);

            if (userType === UserRole.COMPANY) {
                response = await axios.post('http://localhost:8080/auth/registerCompany', requestData);
            }
            else if( userType ===  UserRole.REGULAR_USER){
                response = await axios.post('http://localhost:8080/auth/registerRegularUser', requestData);
            }

            console.log(response);
            const { id, role } = response.data;
            sessionStorage.setItem('userId', id);
            sessionStorage.setItem('userRole', role);

            // Registration success, redirect to login page
            navigate('/home');
        } catch (error) {
            console.log(error);
            setErrorMessage('Registration failed. Please try again.\n' + error.response.data);
        }
    };

    return (
        <div className="register-container">
            <h2 className="register-heading">Register</h2>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <form className="register-form">
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} className="form-input" />
                    {errors.email && <span className="error-text">{errors.email}</span>}
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} className="form-input" />
                    {errors.password && <span className="error-text">{errors.password}</span>}
                </div>
                <div className="form-group">
                    <label htmlFor="userType">User Type:</label>
                    <select id="userType" value={userType} onChange={(e) => setUserType(e.target.value)} className="form-input">
                        <option value={UserRole.REGULAR_USER}>{UserRole.REGULAR_USER}</option>
                        <option value={UserRole.COMPANY}>{UserRole.COMPANY}</option>
                    </select>
                    {errors.userType && <span className="error-text">{errors.userType}</span>}
                </div>
                {userType === UserRole.COMPANY && (
                    <div className="form-group">
                        <label htmlFor="companyName">Company Name:</label>
                        <input type="text" id="companyName" value={companyName} onChange={(e) => setCompanyName(e.target.value)} className="form-input" />
                        {errors.companyName && <span className="error-text">{errors.companyName}</span>}
                    </div>
                )}
                {userType === UserRole.REGULAR_USER && (
                    <>
                        <div className="form-group">
                            <label htmlFor="firstName">First Name:</label>
                            <input type="text" id="firstName" value={firstName} onChange={(e) => setFirstName(e.target.value)} className="form-input" required />
                            {errors.firstName && <span className="error-text">{errors.firstName}</span>}
                        </div>
                        <div className="form-group">
                            <label htmlFor="lastName">Last Name:</label>
                            <input type="text" id="lastName" value={lastName} onChange={(e) => setLastName(e.target.value)} className="form-input" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="gender">Gender:</label>
                            <select id="gender" value={gender} onChange={(e) => setGender(e.target.value)} className="form-input">
                                <option value=""></option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="phoneNumber">Phone Number:</label>
                            <input type="text" id="phoneNumber" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} className="form-input" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="birthdate">Birthdate:</label>
                            <input type="date" id="birthdate" value={birthdate} onChange={(e) => setBirthdate(e.target.value)} className="form-input" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="address">Address:</label>
                            <textarea id="address" value={address} onChange={(e) => setAddress(e.target.value)} className="form-input" />
                        </div>
                    </>
                )}
                <button type="button" onClick={handleRegister} className="register-button">Register</button>
            </form>
            <p className="login-text">Already have an account? <Link to="/login">Login</Link></p>
        </div>

    );
};

export default RegisterScreen;
