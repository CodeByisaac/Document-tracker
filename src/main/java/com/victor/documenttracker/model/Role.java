package com.victor.documenttracker.model;


import java.util.List;

public enum Role{
    USER("ROLE_USER", List.of("READ_OWN_DOCUMENT")),
    MANAGER("ROLE_MANAGER", List.of("READ_TEAM_DOCUMENTS", "MANAGER_TEAM")),
    ADMIN("ROLE_ADMIN", List.of("READ_ALL", "DELETE_ANY", "MANAGE_USERS"));

    private final String authority;
    private final List<String> permissions;

    Role(String authority, List<String> permissions) {
        this.authority = authority;
        this.permissions = permissions;
    }

    public String getAuthority(){
        return authority;
    }

    public List<String> getPermissions(){
        return permissions;
    }
}