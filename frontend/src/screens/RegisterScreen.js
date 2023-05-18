import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

const RegisterScreen = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState('Regular User');
    const [companyName, setCompanyName] = useState('');
    const [error, setError] = useState('');

    const handleRegister = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/register', {
                email: email,
                password: password,
                userType: userType,
                companyName: userType === 'Company' ? companyName : '',
            });

            // Registration success, redirect to login page
            navigate('/login');
        } catch (error) {
            setError('Registration failed. Please try again.');
        }
    };

    return (
        <div>
            <h2>Register</h2>
            {error && <p>{error}</p>}
            <form>
                <div>
                    <label>Email:</label>
                    <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <div>
                    <label>User Type:</label>
                    <select value={userType} onChange={(e) => setUserType(e.target.value)}>
                        <option value="Regular User">Regular User</option>
                        <option value="Company">Company</option>
                    </select>
                </div>
                {userType === 'Company' && (
                    <div>
                        <label>Company Name:</label>
                        <input type="text" value={companyName} onChange={(e) => setCompanyName(e.target.value)} />
                    </div>
                )}
                <button type="button" onClick={handleRegister}>Register</button>
            </form>
            <p>Already have an account? <Link to="/login">Login</Link></p>
        </div>
    );
};

export default RegisterScreen;
