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
        <div>
            <h2>Register</h2>
            {errorMessage && <p>{errorMessage}</p>}
            <form>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                    {errors.email && <span style={{ color: "red" }}>{errors.email}</span>}
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                    {errors.password && <span style={{ color: "red" }}>{errors.password}</span>}
                </div>
                <div>
                    <label>User Type:</label>
                    <select value={userType} onChange={(e) => setUserType(e.target.value)}>
                        <option value={UserRole.REGULAR_USER}>{UserRole.REGULAR_USER}</option>
                        <option value={UserRole.COMPANY}>{UserRole.COMPANY}</option>
                    </select>
                    {errors.userType && <span style={{ color: "red" }}>{errors.userType}</span>}
                </div>
                {userType === UserRole.COMPANY && (
                    <div>
                        <label>Company Name:</label>
                        <input type="text" value={companyName} onChange={(e) => setCompanyName(e.target.value)} />
                        {errors.companyName && <span style={{ color: "red" }}>{errors.companyName}</span>}
                    </div>
                )}
                {userType === UserRole.REGULAR_USER && (
                    <>
                        <div>
                            <label>First Name:</label>
                            <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value) } required="required"/>
                            {errors.firstName && <span style={{ color: "red" }}>{errors.firstName}</span>}
                        </div>
                        <div>
                            <label>Second Name:</label>
                            <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                        </div>
                        <div>
                            <label>Gender:</label>
                            <select value={gender} onChange={(e) => setGender(e.target.value)}>
                                <option value=""></option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>
                        <div>
                            <label>Phone Number:</label>
                            <input type="text" value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} />
                        </div>
                        <div>
                            <label>Birthdate:</label>
                            <input type="date" value={birthdate} onChange={(e) => setBirthdate(e.target.value)} />
                        </div>
                        <div>
                            <label>Address:</label>
                            <textarea value={address} onChange={(e) => setAddress(e.target.value)} />
                        </div>
                    </>
                    )}
                <button type="button" onClick={handleRegister}>Register</button>
            </form>
            <p>Already have an account? <Link to="/login">Login</Link></p>
        </div>
    );
};

export default RegisterScreen;
