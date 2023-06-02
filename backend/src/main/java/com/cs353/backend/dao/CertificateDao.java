package com.cs353.backend.dao;

import com.cs353.backend.model.entities.Certificate;

import java.util.ArrayList;
import java.util.List;

public interface CertificateDao {

    void addCertificate(Certificate certificate);

    void removeCertificate(Certificate certificate);

    List<Certificate> getCertificates(int userId);



}
