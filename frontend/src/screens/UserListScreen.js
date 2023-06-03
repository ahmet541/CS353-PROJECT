import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import AuthorLink from "../components/AuthorLink";
import '../css/UserListScreen.css';

const UserListScreen = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [filterOption, setFilterOption] = useState('name');
    const [sortOption, setSortOption] = useState('');
    const [userType, setUserType] = useState('All');

    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetchUserList();
    }, [searchQuery, filterOption, sortOption, userType]);

    const fetchUserList = async () => {
        try {
            console.log(sortOption)   ;
            const response = await axios.get('http://localhost:8080/search-user', {
                params: {
                    searchQuery:searchQuery,
                    sortOption,
                    filterOption,
                    userType
                }
            });
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching user list:', error);
        }
    };

    const handleSearch2 = (query) => {
        const newSearchQuery = query.trim();
        if (newSearchQuery !== '') {
            setSearchQuery(newSearchQuery);
        } else {
            setSearchQuery('');
        }

    };

    const handleSearch = () => {
        fetchUserList();
    };

    const handleFilterChange = (option) => {
        setFilterOption(option);
    };

    const handleSortChange = (option) => {
        setSortOption(option);
    };
    const handleUserTypeChange = (option) => {
        setUserType(option);
    };
    return (
        <div>
            <Navbar />
            <div className="container user-list-container">
                <div className="search-bar mb-3">
                    <div className="row">
                        <div className="col-sm-6 col-md-4 col-lg-3">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Search..."
                                value={searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                            />
                        </div>
                        <div className="col-sm-6 col-md-4 col-lg-3">
                            <select
                                className="form-control"
                                value={filterOption}
                                onChange={(e) => handleFilterChange(e.target.value)}
                            >
                                <option value="">Filter by...</option>
                                <option value="name">Name</option>
                                {/* Add more filter options here */}
                            </select>
                        </div>
                        <div className="col-sm-6 col-md-4 col-lg-3">
                            <select
                                className="form-control"
                                value={sortOption}
                                onChange={(e) => handleSortChange(e.target.value)}
                            >
                                <option value="">Sort by...</option>
                                <option value="asc">Ascending</option>
                                <option value="desc">Descending</option>
                                {/* Add more sort options here */}
                            </select>
                        </div>
                        <div className="col-sm-6 col-md-4 col-lg-3">
                            <select
                                className="form-control"
                                value={userType}
                                onChange={(e) => handleUserTypeChange(e.target.value)}
                            >
                                <option value="All">All</option>
                                <option value="Regular User">Regular User</option>
                                <option value="Company">Company</option>
                                {/* Add more sort options here */}
                            </select>
                        </div>
                    </div>
                    <div className="row mt-2">
                        <div className="col">
                            <button className="btn btn-primary" onClick={handleSearch}>Search</button>
                        </div>
                    </div>
                </div>
                <ul className="list-group">
                    {users.map((user, index) => (
                        <li key={user.userId} className={`list-group-item ${index % 2 === 0 ? 'bg-light' : 'bg-white'}`}>
                            <AuthorLink authorId={user.userId} />
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};


export default UserListScreen;
