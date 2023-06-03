package com.cs353.backend.mapper;

import com.cs353.backend.model.dto.ApplicantDTO;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.Recruiter;
import com.cs353.backend.model.entities.RegularUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantMapper implements RowMapper<ApplicantDTO> {
    @Override
    public ApplicantDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

        ApplicantDTO applicant = new ApplicantDTO();

        applicant.setId(rs.getInt("id"));
        applicant.setFirstName(rs.getString("first_name"));
        applicant.setLastName(rs.getString("last_name"));
        applicant.setGender(rs.getString("gender"));
        applicant.setPhoneNumber(rs.getString("phone_number"));
        applicant.setBirthdate(rs.getDate("birthdate"));
        applicant.setAddress(rs.getString("address"));
        return applicant;
    }
}
