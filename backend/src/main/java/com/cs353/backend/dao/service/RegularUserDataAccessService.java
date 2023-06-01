package com.cs353.backend.dao.service;

import com.cs353.backend.Enum.UserRole;
import com.cs353.backend.dao.AccountDao;
import com.cs353.backend.dao.RegularUserDao;
import com.cs353.backend.mapper.AccountMapper;
import com.cs353.backend.mapper.RegularUserMapper;
import com.cs353.backend.model.dto.LoginDTO;
import com.cs353.backend.model.dto.LoginResponseDTO;
import com.cs353.backend.model.entities.Account;
import com.cs353.backend.model.entities.RegularUser;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor

public class RegularUserDataAccessService implements RegularUserDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * This method gets all regular users
     * @return all attributes of all regular users with its super clasess
     */
    @Override
    public List<RegularUser> getAllRegularUsers() {
        String sql = """
                    SELECT RU.*, A.email, A.password, U.profile_description
                    FROM regular_user RU
                    JOIN "User" U ON RU.id = U.id
                    JOIN account A ON U.id = A.id;
                    """;

        List<RegularUser> regularUsers = jdbcTemplate.query(sql, new RegularUserMapper());

        return regularUsers;
    }

    @Override
    public RegularUser getRegularUserById(int id) {
        String sql = """
                     SELECT RU.*, U.profile_description, U.avatar 
                     FROM regular_user RU 
                     JOIN "User" U ON U.id = RU.id
                     WHERE RU.id = ?
                     """;
        return jdbcTemplate.queryForObject(sql, new RegularUserMapper(), id);
    }

    @Override
    public void createRegularUser(@NotNull RegularUser regularUser) {
        String sql = "INSERT INTO Regular_User (id, first_name, last_name, gender, phone_number, birthdate, address) VALUES (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, regularUser.getId(), regularUser.getFirstName(), regularUser.getLastName(), regularUser.getGender(), regularUser.getPhoneNumber(), regularUser.getBirthdate(), regularUser.getAddress());
    }

    @Override
    public void updateRegularUser(RegularUser regularUser) {
        String sql = "UPDATE Regular_User SET first_name = ?, last_name = ?, gender = ?, phone_number = ?, birthdate = ?, address = ? WHERE id = ?;";
        jdbcTemplate.update(sql, regularUser.getFirstName(), regularUser.getLastName(), regularUser.getGender(), regularUser.getPhoneNumber(), regularUser.getBirthdate(), regularUser.getAddress(), regularUser.getId());
    }


    @Override
    public void deleteRegularUser(int id) {

    }
}
