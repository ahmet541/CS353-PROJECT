import React from 'react';

const JobOpening = ({ jobOpeningId, employmentStatus, explanation, dueDate, rolePro, location, workType }) => {
    return (
        <div style={{
            border: '1px solid #ccc',
            borderRadius: '4px',
            padding: '16px',
            marginBottom: '16px',
            backgroundColor: '#fff',
            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
            textAlign: 'center'
        }}>
            <h3>{employmentStatus}</h3>
            <p style={{
                border: '1px solid #ccc',
                borderRadius: '4px',
                fontSize: "20px",
                backgroundColor: '#fff',
                marginLeft: "200px",
                marginRight: "200px",
                textAlign: "left"
            }}>Explanation: {explanation}</p>
            <p style={{
                marginLeft: "200px",
                marginRight: "200px",
                textAlign: "left"}}>Due Date: {dueDate}</p>
            <p style={{
                marginLeft: "200px",
                marginRight: "200px",
                textAlign: "left"}}>Role Pro: {rolePro}</p>
            <p style={{
                marginLeft: "200px",
                marginRight: "200px",
                textAlign: "left"}}>Location: {location}</p>
            <p style={{
                marginLeft: "200px",
                marginRight: "200px",
                textAlign: "left"}}>Work Type: {workType}</p>
        </div>
    );
};

export default JobOpening;
