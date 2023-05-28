package com.cs353.backend.mapper;

import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.RegularUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegularUserMapper implements RowMapper<RegularUser> {

    @Override
    public RegularUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegularUser regularUser = new RegularUser();

        regularUser.setId(rs.getInt("id"));
        regularUser.setFirstName(rs.getString("first_name"));
        regularUser.setLastName(rs.getString("last_name"));
        regularUser.setGender(rs.getString("gender"));
        regularUser.setPhoneNumber(rs.getString("phone_number"));
        regularUser.setBirthdate(rs.getDate("birthdate"));
        regularUser.setAddress(rs.getString("address"));
        regularUser.setProfileDescription(rs.getString("profile_description"));
        regularUser.setUserAvatar(rs.getString("avatar"));
        return regularUser;
    }
}
