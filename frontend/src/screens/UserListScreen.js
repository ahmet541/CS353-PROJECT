import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

const UserListScreen = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [filterOption, setFilterOption] = useState('');
    const [sortOption, setSortOption] = useState('');
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const storedSearchQuery = sessionStorage.getItem('userlistsearchtext');
        if (storedSearchQuery) {
            setSearchQuery(storedSearchQuery);
        }
    }, []);

    useEffect(() => {
        fetchUserList();
    }, [searchQuery, filterOption, sortOption]);

    const fetchUserList = async () => {
        try {
            const response = await axios.get('/searchUser/', {
                params: {
                    query: searchQuery,
                    sort: sortOption,
                    field: filterOption,
                },
            });
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching user list:', error);
        }
    };

    const handleSearch = () => {
        sessionStorage.setItem('userlistsearchtext', searchQuery);
    };

    const handleFilterChange = (option) => {
        setFilterOption(option);
    };

    const handleSortChange = (option) => {
        setSortOption(option);
    };

    return (
        <div>
            <Navbar onSearch={handleSearch} />
            <div className="user-list-container">
                <div className="search-bar">
                    <input
                        type="text"
                        placeholder="Search..."
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                    />
                    <button onClick={handleSearch}>Search</button>
                    <select
                        value={filterOption}
                        onChange={(e) => handleFilterChange(e.target.value)}
                    >
                        <option value="">Filter by...</option>
                        <option value="name">Name</option>
                        {/* Add more filter options here */}
                    </select>
                    <select
                        value={sortOption}
                        onChange={(e) => handleSortChange(e.target.value)}
                    >
                        <option value="">Sort by...</option>
                        <option value="asc">Ascending</option>
                        <option value="desc">Descending</option>
                        {/* Add more sort options here */}
                    </select>
                </div>

                {/* Render the user list based on the 'users' state */}
                <ul>
                    {users.map((user) => (
                        <li key={user.id}>{user.name}</li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default UserListScreen;
