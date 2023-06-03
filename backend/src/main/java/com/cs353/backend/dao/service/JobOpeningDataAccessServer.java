package com.cs353.backend.dao.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cs353.backend.Enum.EmploymentStatus;
import com.cs353.backend.dao.JobOpeningDao;
import com.cs353.backend.mapper.JobOpeningMapper;
import com.cs353.backend.mapper.JobOppeningApplicationMapper;
import com.cs353.backend.model.dto.JobOpeningApplicationDTO;
import com.cs353.backend.model.dto.JobOpeningDTO;
import com.cs353.backend.model.entities.JobOpening;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class JobOpeningDataAccessServer implements JobOpeningDao{

    private JdbcTemplate jdbcTemplate;
    @Override
    public JobOpening createJobOpening(JobOpeningDTO jobOpening, int userId) {
        String sql = """
                INSERT INTO JobOpening (employment_status, explanation, due_date, role_pro, location, work_type)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING job_opening_id
                """;
        int id = jdbcTemplate.queryForObject(sql, Integer.class, Enum.valueOf(EmploymentStatus.class, jobOpening.getEmploymentStatus()).getValue(), jobOpening.getExplanation(), jobOpening.getDueDate(), jobOpening.getRolePro(), jobOpening.getLocation(), jobOpening.getWorkType() );
        JobOpening newJobOpening = new JobOpening();
        newJobOpening.setJobOpeningID(id);
        newJobOpening.setLocation(jobOpening.getLocation());
        newJobOpening.setExplanation(jobOpening.getExplanation());
        newJobOpening.setRolePro(jobOpening.getRolePro());
        newJobOpening.setDueDate(jobOpening.getDueDate());
        newJobOpening.setEmploymentStatus(jobOpening.getEmploymentStatus());
        newJobOpening.setWorkType(jobOpening.getWorkType());
        return newJobOpening;
    }

    @Override
    public List<JobOpening> getAllJobOpenings() {
        String sql = """
                SELECT *
                FROM jobopening
                ORDER BY due_date
                """;
        return jdbcTemplate.query(sql, new JobOpeningMapper());
    }

    @Override
    public List<JobOpening> getJobOpeningsByFilter(JobOpeningDTO jobOpeningDTO) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM jobopening WHERE 1=1");

        List<Object> params = new ArrayList<>();

        if (jobOpeningDTO.getEmploymentStatus() != null && !jobOpeningDTO.getEmploymentStatus().isEmpty()) {
            queryBuilder.append(" AND employment_status = ?");
            params.add(Enum.valueOf(EmploymentStatus.class, jobOpeningDTO.getEmploymentStatus().toUpperCase()).getValue());
        }

        if (jobOpeningDTO.getDueDate() != null) {
            queryBuilder.append(" AND due_date = ?");
            params.add(jobOpeningDTO.getDueDate());
        }

        if(jobOpeningDTO.getRolePro() != null && !jobOpeningDTO.getRolePro().isEmpty()) {
            queryBuilder.append(" AND role_pro = ?");
            params.add(jobOpeningDTO.getRolePro());
        }

        if(jobOpeningDTO.getLocation() != null && !jobOpeningDTO.getLocation().isEmpty()) {
            queryBuilder.append(" AND location = ?");
            params.add(jobOpeningDTO.getLocation());
        }

        if(jobOpeningDTO.getWorkType() != null && !jobOpeningDTO.getWorkType().isEmpty()) {
            queryBuilder.append(" AND work_type = ?");
            params.add(jobOpeningDTO.getWorkType());
        }

        if(jobOpeningDTO.getMinDueDate() != null && jobOpeningDTO.getMaxDueDate() != null){
            queryBuilder.append(" AND due_date between ? AND ?");
            params.add(jobOpeningDTO.getMinDueDate());
            params.add(jobOpeningDTO.getMaxDueDate());
        }

        return jdbcTemplate.query(queryBuilder.toString(), new JobOpeningMapper(), params.toArray());
    }

    @Override
    public void addJobField(String field, int jobOpeningId) {
        try {
            String sql1 = """
                SELECT EXISTS (SELECT 1 
                               FROM field 
                               WHERE field_name ILIKE ?)
                """;
            boolean fieldExist = jdbcTemplate.queryForObject(sql1, Boolean.class, field);

            if (!fieldExist){
                System.out.println("The field \"" + field + "\" does not exist");
                return;
            }
        } catch (EmptyResultDataAccessException e){
            System.out.println("The field table is empty: " + e.getMessage());
        }

        String sql = """
                INSERT INTO job_field (field_name, job_opening_id)
                VALUES (?, ?)
                """;
        jdbcTemplate.update(sql,field, jobOpeningId);
    }

    //NOT TESTED
    @Override
    public boolean applyJobOpening(JobOpeningApplicationDTO jobOpeningApplicationDTO) {
        //If this method is executed, it is assumed that jobOpeningApplicationDTO's all properties are properly initialized.
        String sql = """
                INSERT INTO application (user_id, job_opening_id, application_status, experience, skills, education_lvl, cv)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(sql, new JobOppeningApplicationMapper(),
                jobOpeningApplicationDTO.getUserId(),
                jobOpeningApplicationDTO.getJobOpeningId(),
                jobOpeningApplicationDTO.getApplicationStatus(),
                jobOpeningApplicationDTO.getExperience(),
                jobOpeningApplicationDTO.getSkills(),
                jobOpeningApplicationDTO.getEducationLvl(),
                jobOpeningApplicationDTO.getCv()) > 0;  //BEWARE of comparison!
    }

    @Override
    public List<JobOpening> getMyJobOpenings(int recruiterId) {
        String sql = """
                SELECT  JO.*
                FROM jobopening JO
                JOIN open_position OP ON OP.job_opening_id = JO.job_opening_id; 
                where OP.recruiter_id = ?
                """;

        return jdbcTemplate.query(sql, new JobOpeningMapper(), recruiterId);

    }
}
