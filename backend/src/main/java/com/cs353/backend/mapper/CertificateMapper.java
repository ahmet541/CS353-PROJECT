package com.cs353.backend.mapper;

import com.cs353.backend.dao.CertificateDao;
import com.cs353.backend.model.dto.ChatDTO;
import com.cs353.backend.model.entities.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateMapper implements RowMapper<Certificate> {

    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setUserId(resultSet.getInt("user_id"));
        certificate.setName(resultSet.getString("certificate_name"));
        certificate.setContent(resultSet.getString("content"));
        return  certificate;
    }
}
