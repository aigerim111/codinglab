package com.example.project1.user;

public enum UserPermission {
    INFO_READ("info:read"),
    INFO_WRITE("info:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
