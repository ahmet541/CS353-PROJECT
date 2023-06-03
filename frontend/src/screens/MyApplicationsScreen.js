import React, { useEffect, useState } from 'react';
import '../css/MyApplicationsScreen.css';
import axios from "axios";

const MyApplicationsScreen = () => {
    const [applications, setApplications] = useState([{
        jobOpeningId:1,
        applicationStatus:"Not Accepted"
    }, {
        jobOpeningId:2,
        applicationStatus:"Accepted"
    }]);

    // Fetch applications data from API or database
    useEffect(() => {
        // Example API call to fetch applications
        axios.get(`http://localhost:8080/regular-user/myApplications/${sessionStorage.getItem("userId")}`)
            .then(response => setApplications(response.data))
            .catch(error => console.log(error));
    }, []);

    return (
        <div className="my-applications-container">
            <h2 className="my-applications-heading">My Applications</h2>
            {applications.length > 0 ? (
                <table className="applications-table">
                    <thead>
                    <tr>
                        <th>Job Opening ID</th>
                        <th>Application Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {applications.map(application => (
                        <tr key={application.id}>
                            <td>{application.jobOpeningId}</td>
                            <td>{application.applicationStatus}</td>
                            <td>{application.applicationDate}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            ) : (
                <p className="no-applications-message">No applications found.</p>
            )}
        </div>
    );
};

export default MyApplicationsScreen;
