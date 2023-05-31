import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import 'bootstrap/dist/js/bootstrap.bundle.min.js'; // Import Bootstrap JS
import '../css/Navbar.css'; // Import the CSS file for custom styling

const Navbar = ({ userId }) => {
    const [searchQuery, setSearchQuery] = useState('');

    const handleLogout = () => {
        // Perform logout logic here
        // Redirect the user to the login page
    };

    const handleSearch = (e) => {
        e.preventDefault();
        // Perform search logic here based on the searchQuery state
        console.log(`Searching for:}`, searchQuery);
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav me-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/home">
                                Home
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={`/profile/${userId}`}>
                                Profile
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/messages">
                                Messages
                            </Link>
                        </li>
                        <li className="nav-item">
                            <div className="navbar__search d-flex align-items-center">
                                <form className="navbar__search-form flex-grow-1" onSubmit={handleSearch}>
                                    <div className="input-group">
                                        <input
                                            type="text"
                                            className="form-control"
                                            placeholder="Search people..."
                                            value={searchQuery}
                                            onChange={(e) => setSearchQuery(e.target.value)}
                                        />
                                        <button type="submit" className="btn btn-primary">
                                            Search
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </li>

                    </ul>

                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <button className="btn btn-primary bg-danger" onClick={handleLogout}>
                                Logout
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
