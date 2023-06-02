package com.cs353.backend.dao.service;

import com.cs353.backend.dao.CertificateDao;
import com.cs353.backend.mapper.CertificateMapper;
import com.cs353.backend.model.entities.Certificate;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CertificateDataAccessService implements CertificateDao {

    private JdbcTemplate jdbcTemplate;
    @Override
    public void addCertificate(Certificate certificate) {
        String sql = """
                INSERT INTO Certificate_Skills (certificate_name, content, user_id)
                Values(?,?,?);
                """;
        jdbcTemplate.update(sql, certificate.getName(), certificate.getContent(), certificate.getUserId());
    }

    @Override
    public void removeCertificate(Certificate certificate) {
        String sql = """
        DELETE FROM Certificate_Skills 
        WHERE user_id = ? AND certificate_name = ?
        """;
        jdbcTemplate.update(sql, certificate.getUserId(), certificate.getName());
    }

    @Override
    public List<Certificate> getCertificates(int userId) {
        String sql = "SELECT user_id, certificate_name, content FROM Certificate_Skills WHERE user_id = ?";
        return jdbcTemplate.query(sql, new CertificateMapper(),userId);

    }
}
