package com.cs353.backend.dao.service;

import com.cs353.backend.dao.ApplicationDao;
import com.cs353.backend.mapper.ApplicationDTOMapper;
import com.cs353.backend.mapper.ApplicationMapper;
import com.cs353.backend.model.dto.ApplicationDTO;
import com.cs353.backend.model.entities.RegularUser;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@AllArgsConstructor
public class ApplicationDataAccessService implements ApplicationDao {

    private JdbcTemplate jdbcTemplate;
    public List<ApplicationDTO> getMyApplications(int userId){
        String sql= """
                SELECT A.*
                FROM application A
                where user_id = ? 
                """;
        return jdbcTemplate.query(sql, new ApplicationDTOMapper(),userId);
    }

    public ApplicationDTO getApplication(int userId, int job_opening_id){
        String sql= """
                SELECT A.*
                FROM application A
                where user_id = ? and job_opening_id = ?
                """;
        return jdbcTemplate.queryForObject(sql, new ApplicationDTOMapper(),userId, job_opening_id);
    }

    @Override
    public List<ApplicationDTO> getApplicationsOfJobOpening(int jobOpeningId) {
        String sql= """
                SELECT A.*
                FROM application A
                where job_opening_id = ? 
                """;
        return jdbcTemplate.query(sql, new ApplicationDTOMapper(), jobOpeningId);
    }

    @Override
    public boolean applyJobOpening(ApplicationDTO applicationDTO) {
        String sql = """
                INSERT INTO application
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql,
                applicationDTO.getUser_id(),
                applicationDTO.getJob_opening_id(),
                applicationDTO.getApplicationDate(),
                applicationDTO.getApplication_status(),
                applicationDTO.getExperience(),
                applicationDTO.getSkills(),
                applicationDTO.getEducation_lvl(),
                applicationDTO.getCv()) > 0;
    }

}
