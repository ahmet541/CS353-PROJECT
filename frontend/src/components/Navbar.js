import React, {useState} from 'react';
import { Link } from 'react-router-dom';
import '../css/Navbar.css'; // Import the CSS file for styling

const Navbar = ({ children }) => {

    const [searchQuery, setSearchQuery] = useState('');

    const handleLogout = () => {
        // Perform logout logic here
        // Redirect the user to the login page
    };
    const handleSearch = (e) => {
        e.preventDefault();
        // Perform search logic here based on the searchQuery state
        console.log('Searching for:', searchQuery);
    };
    return (
        <div className="navbar">
            <nav>
                <ul className="navbar-nav">
                    <li className="nav-item">
                        <Link to="/" className="nav-link">
                            Home
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/profile" className="nav-link">
                            Profile
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link to="/messages" className="nav-link">
                            Messages
                        </Link>
                    </li>
                    <li className="nav-item">
                        <button className="logout-btn" onClick={handleLogout}>
                            Logout
                        </button>
                    </li>
                </ul>
                <div className="navbar__search">
                    <form className="navbar__search-form" onSubmit={handleSearch}>
                        <input
                            type="text"
                            className="navbar__search-input"
                            placeholder="Search people..."
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                        <button type="submit" className="navbar__search-button">Search</button>
                    </form>
                </div>
            </nav>
            <main>{children}</main>
        </div>
    );
};

export default Navbar;
