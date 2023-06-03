import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

const CreateJobAdvertScreen = () => {
    const navigate = useNavigate();
    const [position, setPosition] = useState('');
    const [employmentStatus, setEmploymentStatus] = useState('');
    const [profession, setProfession] = useState('');
    const [location, setLocation] = useState('');
    const [workType, setWorkType] = useState('');
    const [explanation, setExplanation] = useState('');
    const [dueDate, setDueDate] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [field, setField] = useState('');

    const handleCreateJobAdvert = async () => {
        try {
            if (!position || !employmentStatus || !profession || !location || !workType || !explanation || !dueDate) {
                setErrorMessage('Please fill in all required fields');
                return;
            }

            const jobAdvertData = {
                position: position,
                employmentStatus: employmentStatus.toUpperCase(),
                rolePro: profession,
                location: location,
                workType: workType,
                explanation: explanation,
                dueDate: new Date(dueDate),
                field: field
            };

            // Send the job advert data to the server for creation
            const response = await axios.post("http://localhost:8080/jobopening/" + sessionStorage.getItem("userId"), jobAdvertData);
            console.log(response);

            // Job advert creation success, navigate to a success page or perform other actions
            navigate('/jobopenings');
        } catch (error) {
            console.log(error);
            setErrorMessage('Job advert creation failed. Please try again.');
        }
    };

    return (
        <div className="container">
            <h2>Create Job Advert</h2>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <form>
                <div className="form-group">
                    <label>Position:</label>
                    <input type="text" className="form-control" value={position} onChange={(e) => setPosition(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Field:</label>
                    <select className="form-control" value={field} onChange={(e) => setField(e.target.value)}>
                        <option value="">Select Field</option>
                        <option value="AI">AI</option>
                        <option value="Web Development">Web Development</option>
                        <option value="Consulting">Consulting</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Employment Status:</label>
                    <select className="form-control" value={employmentStatus} onChange={(e) => setEmploymentStatus(e.target.value)}>
                        <option value="">Select Employment Status</option>
                        <option value="internship">Internship</option>
                        <option value="parttime">Part-time</option>
                        <option value="fulltime">Full-time</option>
                        <option value="oncall">On-call</option>
                        <option value="freelancer">Freelancer</option>
                        <option value="temporary">Temporary</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Profession:</label>
                    <input type="text" className="form-control" value={profession} onChange={(e) => setProfession(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Location:</label>
                    <input type="text" className="form-control" value={location} onChange={(e) => setLocation(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Work Type:</label>
                    <select className="form-control" value={workType} onChange={(e) => setWorkType(e.target.value)}>
                        <option value="">Select Work Type</option>
                        <option value="Online">Online</option>
                        <option value="Remote">Remote</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Explanation:</label>
                    <textarea className="form-control" value={explanation} onChange={(e) => setExplanation(e.target.value)} />
                </div>
                <div className="form-group">
                    <label>Due Date:</label>
                    <input type="date" className="form-control" value={dueDate} onChange={(e) => setDueDate(e.target.value)} />
                </div>
                <button type="button" className="btn btn-primary" onClick={handleCreateJobAdvert}>Create Job Advert</button>
            </form>
            <p>Back to <Link to="/home">Home</Link></p>
        </div>
    );
};

export default CreateJobAdvertScreen;
