import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate , Link } from 'react-router-dom';

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
        <div>
            <h2>Login</h2>
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
                <button type="button" onClick={handleLogin}>Login</button>
            </form>
            <p>Don't have an account? <Link to="/register">Register</Link></p>
        </div>
    );
};

export default LoginScreen;
