import React, { useEffect, useState } from 'react';
import '../css/MyApplicationsScreen.css';
import axios from "axios";

const MyApplicationsScreen = () => {
    const [applications, setApplications] = useState([{
        job_opening_id:1,
        application_status:"Not Accepted"
    }, {
        job_opening_id:2,
        application_status:"Accepted"
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
                        <th>Application Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    {applications.map(application => (
                        <tr key={application.job_opening_id}>
                            <td>{application.job_opening_id}</td>
                            <td>{application.application_status ? "Accepted" : "Not Accepted"} </td>
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
