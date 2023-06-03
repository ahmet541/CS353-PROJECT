import React, { useEffect, useState } from 'react';
import '../css/MyAdvertsScreen.css';

const MyAdvertsScreen = () => {
    const [jobOpenings, setJobOpenings] = useState([{
        jobOpeningId: 1
    }, {
        jobOpeningId: 2
    }]);
    const [applicants, setApplicants] = useState([{
        userId:1,
        fullName: "iso"
    }, {
        userId:2,
        fullName: "isos"
    }]);

    // Fetch job openings data from API or database
    useEffect(() => {
        // Example API call to fetch job openings
        fetch('/api/job-openings')
            .then(response => response.json())
            .then(data => setJobOpenings(data))
            .catch(error => console.log(error));
    }, []);

    const handleHire = (jobOpeningId, userId) => {
        // Handle the "Hire" button click event
        console.log(`Hiring user ${userId} for job opening ${jobOpeningId}`);
    };

    return (
        <div className="my-adverts-container">
            <h2 className="my-adverts-heading">My Adverts</h2>
            {jobOpenings.length > 0 ? (
                <div className="job-openings-list">
                    {jobOpenings.map(jobOpening => (
                        <div key={jobOpening.id} className="job-opening-item">
                            <div className="job-opening-info">
                                <h3 className="job-opening-id">Job Opening ID: {jobOpening.jobOpeningId}</h3>
                                <h4 className="applicants-heading">Applicants:</h4>
                                <ul className="applicants-list">
                                    {applicants.map(applicant => (
                                        <li key={applicants.userId} className="applicant-item">
                                            <span className="applicant-name">{applicant.fullName}</span>
                                            <button
                                                className="hire-button"
                                                onClick={() => handleHire(jobOpening.jobOpeningId, applicant.userId)}
                                            >
                                                Hire
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <p className="no-job-openings-message">No job openings found.</p>
            )}
        </div>
    );
};

export default MyAdvertsScreen;