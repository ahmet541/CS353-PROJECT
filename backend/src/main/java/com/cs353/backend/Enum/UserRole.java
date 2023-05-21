package com.cs353.backend.Enum;

public enum UserRole {
    ACCOUNT("Account"),
    USER("User"),
    REGULAR_USER("RegularUser"),
    COMPANY("Company"),
    ADMIN("Admin"),
    CAREER_EXPERT("CareerExpert"),
    RECRUITER("Recruiter");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}