package com.cs353.backend.Enum;

public enum PostType {
    INFORMATIVE("INFORMATIVE"),
    COMMENT("COMMENT");


    private final String value;

    PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
