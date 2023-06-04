import React, { useEffect, useState } from 'react';
import '../css/MyAdvertsScreen.css';
import axios from "axios";
import JobOpening from "../components/JobOpening";
import {wait} from "@testing-library/user-event/dist/utils";

const MyAdvertsScreen = () => {
    const [jobOpenings, setJobOpenings] = useState([]);

    // Fetch job openings data from API or database
    useEffect(() => {
        // Example API call to fetch job openings
        axios.get(`http://localhost:8080/recruiter/myJobOpenings/${sessionStorage.getItem("userId")}`)
            .then((response) => {
                setJobOpenings(response.data)
            })
            .catch((error) => {
                // Handle errors
                console.error("dışardama")
                console.error(error);
            });

    }, []);
    const handleHire = (jobOpeningId, userId) => {
        // Handle the "Hire" button click event
        axios.post(`http://localhost:8080/recruiter/hire/${jobOpeningId}/${userId}`)
            .then((response)=>{
                console.log(`Hiring user ${userId} for job opening ${jobOpeningId}`);
            })
    };

    return (
        <div className="my-adverts-container">
            <h2 className="my-adverts-heading">My Adverts</h2>
            {jobOpenings.length > 0 ? (
                <div className="job-openings-list">
                    {jobOpenings.map(advert => (
                        <div key={advert.jobOpening.jobOpeningID} className="job-opening-item">
                            <div className="job-opening-info">
                                <h3 className="job-opening-id">Job Opening ID: {advert.jobOpening.jobOpeningID}</h3>
                                <h4 className="applicants-heading">Pos: {advert.jobOpening.rolePro} Location: {advert.jobOpening.location}</h4>
                                <h4 className="applicants-heading">Applicants:</h4>
                                <ul className="applicants-list">
                                    {advert.applicants.map(applicant => (
                                        <li key={applicant.id} className="applicant-item">
                                            <span className="applicant-id">id: {applicant.id} name: {applicant.firstName + " " + applicant.lastName}  </span>
                                            <button
                                                className="hire-button"
                                                onClick={() => handleHire(advert.jobOpening.jobOpeningID, applicant.id)}
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
