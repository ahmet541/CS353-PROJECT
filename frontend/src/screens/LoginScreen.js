import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate , Link } from 'react-router-dom';
import "../css/LoginScreen.css"

const LoginScreen = () => {
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/auth/login', {
                email: email,
                password: password,
            }
            );
            console.log(response);
            const { id, role } = response.data;

            // Store user information in session storage
            sessionStorage.setItem('userId', id);
            sessionStorage.setItem('userRole', role);

            // Redirect to home page
            navigate('/home');
        } catch (error) {
            console.log(error);
            setError(error.response.data + ' Please try again.');
        }
    };

    return (
        <div className="login-container">
            <h2 className="login-heading">Login</h2>
            {error && <p className="error-message">{error}</p>}
            <form className="login-form">
                <div className="form-group">
                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} className="form-input" />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} className="form-input" />
                </div>
                <button type="button" onClick={handleLogin} className="login-button">Login</button>
            </form>
            <p className="register-text">Don't have an account? <Link to="/register">Register</Link></p>
        </div>
    );
};

export default LoginScreen;
