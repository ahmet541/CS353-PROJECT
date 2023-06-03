package com.cs353.backend.dao;


public interface OpenPositionDao {

    void addOpenPosition(int companyId, int recruiterId, int jobOpeningId);
    int getCompanyId(int jobOpeningId);
}
