package com.cs353.backend.Enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum UserRole {
    ACCOUNT("ACCOUNT"),
    USER("USER"),
    REGULAR_USER("REGULAR_USER"),
    COMPANY("COMPANY"),
    ADMIN("ADMIN"),
    CAREER_EXPERT("CAREER_EXPERT"),
    RECRUITER("RECRUITER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}