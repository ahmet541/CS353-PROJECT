import React, { useState, useEffect } from 'react';
import axios from 'axios';
import JobOpening from '../components/JobOpening';
import {MDBBtn, MDBCard, MDBCardBody, MDBCardImage} from "mdb-react-ui-kit";
import defaultAvatar from "../pictures/default-avatar.jpg";
import {useNavigate} from "react-router-dom";

const ListJobOpeningsPage = () => {
    const [jobOpenings, setJobOpenings] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const [filters, setFilters] = useState({
        employmentStatus: '',
        location: '',
        workType: '',
    });
    const employmentStatusList = ["", "Internship", "Part-time", "Full-time", "On-call", "Freelancer", "Temporary"]

    const navigate = useNavigate();

    useEffect(() => {
        fetchJobOpenings();
    }, []);

    const fetchJobOpenings = async () => {
        try {
            const response = await axios.get('http://localhost:8080/jobopening/getAllJobOpenings');
            setJobOpenings(response.data);
        } catch (error) {
            setErrorMessage('Failed to fetch job openings.');
        }
    };

    const handleFilterChange = (event) => {
        const { name, value } = event.target;
        setFilters((prevFilters) => ({ ...prevFilters, [name]: value }));
    };

    const handleFilterSubmit = (event) => {
        event.preventDefault();
        // Apply filters and fetch job openings with the selected filters
        fetchFilteredJobOpenings();
    };

    const fetchFilteredJobOpenings = async () => {
        try {
            const response = await axios.post('http://localhost:8080/jobopening/getJobOpeningsByFilter', {
                employmentStatus: filters.employmentStatus.toUpperCase(),
                location: filters.location,
                workType: filters.workType
            });
            setJobOpenings(response.data);
        } catch (error) {
            setErrorMessage('Failed to fetch filtered job openings.');
            console.log(error);
        }
    };

    const handleApply = () => {
        navigate("/apply")
    }

    return (
        <div>
            <h2 style={{ color: 'blue', fontSize: '24px' }}>List Job Openings</h2>
            <form onSubmit={handleFilterSubmit} style={{
                border: '1px solid #ccc',
                borderRadius: '4px',
                padding: '16px',
                marginBottom: '16px',
                backgroundColor: '#fff',
                boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                textAlign: 'left'
            }}>
                <div>
                    <label>Employment Status:</label>
                    <select name="employmentStatus" value={filters.employmentStatus} onChange={handleFilterChange}>
                        <option value="">All</option>
                        <option value="internship">Internship</option>
                        <option value="parttime">Part-Time</option>
                        <option value="fulltime">Full-Time</option>
                        <option value="oncall">On-Call</option>
                        <option value="freelancer">Freelancer</option>
                        <option value="temporary">Temporary</option>
                    </select>
                </div>
                <div>
                    <label>Location:</label>
                    <input type="text" name="location" value={filters.location} onChange={handleFilterChange} />
                </div>
                <div>
                    <label>Work Type:</label>
                    <select name="workType" value={filters.workType} onChange={handleFilterChange}>
                        <option value="">All</option>
                        <option value="Online">Online</option>
                        <option value="Remote">Remote</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>
                </div>
                <button type="submit">Apply Filters</button>
            </form>

            {errorMessage && <p>{errorMessage}</p>}

            <div className="job-openings-wrapper">
                {jobOpenings.map((jobOpening) => (
                    <MDBCard key={jobOpening.jobOpeningId} className="job-opening-item">
                        <MDBCardBody className="d-flex align-items-center">
                            <MDBCardImage
                                className="company-photo"
                                src={defaultAvatar}
                                alt="Company Avatar"
                                fluid
                            />
                            <div className="card-details ms-5">
                                <h5 className="company-name">{jobOpening.companyName ? jobOpening.companyName : "'Insert Company Name'"}</h5>
                                <p className="role-pro">{jobOpening.rolePro}</p>
                                <p className="card-text">
                                    <strong>Due Date:</strong> {jobOpening.dueDate}
                                </p>
                                <p className="card-text">
                                    <strong>Employment Status:</strong> {employmentStatusList[jobOpening.employmentStatus]}
                                </p>
                                <p className="card-text">
                                    <strong>Location:</strong> {jobOpening.location}
                                </p>
                                <p className="card-text">
                                    <strong>Work Type:</strong> {jobOpening.workType}
                                </p>
                            </div>
                            <div className={"ms-5"}>
                                <MDBBtn rounded onClick={handleApply}>
                                    Apply
                                </MDBBtn>
                            </div>
                        </MDBCardBody>
                    </MDBCard>
                ))}
            </div>
        </div>
    );
};

export default ListJobOpeningsPage;
