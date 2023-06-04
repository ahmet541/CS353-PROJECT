import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Form } from 'react-bootstrap';

const AdminScreen = () => {
    const [recruiterEmployeeCounts, setRecruiterEmployeeCounts] = useState([]);
    const [numberOfEmployeesAndRecruiters, setNumberOfEmployeesAndRecruiters] = useState([]);
    const [recruiterSearchQuery, setRecruiterSearchQuery] = useState('');
    const [companySearchQuery, setCompanySearchQuery] = useState('');

    useEffect(() => {
        fetchRecruiterEmployeeCounts();
        fetchNumberOfEmployeesAndRecruiters();
    }, []);

    const fetchRecruiterEmployeeCounts = async () => {
        try {
            const response = await axios.get('http://localhost:8080/admin/employee-recruiter-counts');
            setRecruiterEmployeeCounts(response.data);
        } catch (error) {
            console.error('Error fetching recruiter employee counts:', error);
        }
    };

    const fetchNumberOfEmployeesAndRecruiters = async () => {
        try {
            const response = await axios.get('http://localhost:8080/admin/employee-recruiter-stats');
            setNumberOfEmployeesAndRecruiters(response.data);
        } catch (error) {
            console.error('Error fetching number of employees and recruiters:', error);
        }
    };

    const handleRecruiterSearch = (event) => {
        setRecruiterSearchQuery(event.target.value);
    };

    const handleCompanySearch = (event) => {
        setCompanySearchQuery(event.target.value);
    };

    const filteredRecruiterEmployeeCounts = recruiterEmployeeCounts.filter((data) =>
        data.recruiterName.toLowerCase().includes(recruiterSearchQuery.toLowerCase())
    );

    const filteredNumberOfEmployeesAndRecruiters = numberOfEmployeesAndRecruiters.filter((data) =>
        data.companyName.toLowerCase().includes(companySearchQuery.toLowerCase())
    );

    return (
        <div>
            <h5>Admin Page</h5>

            <h1>Recruiter Employee Counts</h1>
            <Form.Group>
                <Form.Control type="text" placeholder="Search by Recruiter Name" value={recruiterSearchQuery} onChange={handleRecruiterSearch} />
            </Form.Group>
            <Table bordered>
                <thead>
                <tr>
                    <th>Recruiter ID</th>
                    <th>Name</th>
                    <th>Employee Count</th>
                </tr>
                </thead>
                <tbody>
                {filteredRecruiterEmployeeCounts.map((data) => (
                    <tr key={data.recruiterId}>
                        <td>{data.recruiterId}</td>
                        <td>{data.recruiterName}</td>
                        <td>{data.employeeCount}</td>
                    </tr>
                ))}
                </tbody>
            </Table>

            <h1>Number of Employees and Recruiters</h1>
            <Form.Group>
                <Form.Control type="text" placeholder="Search by Company Name" value={companySearchQuery} onChange={handleCompanySearch} />
            </Form.Group>
            <Table bordered>
                <thead>
                <tr>
                    <th>Company ID</th>
                    <th>Company Name</th>
                    <th>Number of Employees</th>
                    <th>Number of Recruiters</th>
                </tr>
                </thead>
                <tbody>
                {filteredNumberOfEmployeesAndRecruiters.map((data) => (
                    <tr key={data.companyId}>
                        <td>{data.companyId}</td>
                        <td>{data.companyName}</td>
                        <td>{data.numEmployees}</td>
                        <td>{data.numRecruiters}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default AdminScreen;
