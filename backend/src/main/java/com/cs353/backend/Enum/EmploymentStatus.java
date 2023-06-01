package com.cs353.backend.Enum;

public enum EmploymentStatus {
    INTERNSHIP(1),
    PARTTIME(2),
    FULLTIME(3),
    ONCALL(4),
    FREELANCER(5),
    TEMPORARY(6);

    private final int value;

    EmploymentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
