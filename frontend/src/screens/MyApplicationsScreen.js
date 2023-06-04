import React, { useEffect, useState } from 'react';
import '../css/MyApplicationsScreen.css';
import axios from "axios";

const MyApplicationsScreen = () => {
    const [applications, setApplications] = useState([]);

    // Fetch applications data from API or database
    useEffect( () => {
        // Example API call to fetch applications
        axios.get(`http://localhost:8080/regular-user/myApplications/${sessionStorage.getItem("userId")}`)
            .then(response => {
                setApplications(response.data);
                console.log(response.data)
            })
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
                        <th>Company Name</th>
                        <th>Position</th>
                        <th>Application Status</th>
                        <th>Application Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    {applications && applications.map(application => (
                        <tr key={application.applicationDTO.job_opening_id}>
                            <td>{application.applicationDTO.job_opening_id}</td>
                            <td>{application.jobOpeningDTO.companyName}</td>
                            <td>{application.jobOpeningDTO.rolePro}</td>
                            <td>{application.applicationDTO.application_status ? "Accepted" : "Not Accepted"} </td>
                            <td>{application.applicationDTO.applicationDate}</td>
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
