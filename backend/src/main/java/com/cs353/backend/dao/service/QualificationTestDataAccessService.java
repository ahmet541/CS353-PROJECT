package com.cs353.backend.dao.service;

import com.cs353.backend.dao.QualificationTestDao;
import com.cs353.backend.model.dto.QualificationTestDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class QualificationTestDataAccessService implements QualificationTestDao {

    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean createQualificationTest(QualificationTestDTO qualificationTestDTO) {

        String companySql = """
                    SELECT OP.company_id
                    FROM open_position OP
                    WHERE OP.job_opening_id = ?
                    """;
        String createQualificationTestSql = """
                INSERT INTO qualificationtest(company_id, job_opening_id, instructions, given_time)
                VALUES ( ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(createQualificationTestSql,
                jdbcTemplate.queryForObject(companySql, Integer.class, qualificationTestDTO.getJob_opening_id()),
                qualificationTestDTO.getJob_opening_id(),
                qualificationTestDTO.getInstructions(),
                qualificationTestDTO.getGiven_time()) > 0;

    }
}
