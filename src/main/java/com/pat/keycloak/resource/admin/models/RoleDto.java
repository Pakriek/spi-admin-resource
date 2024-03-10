package com.pat.keycloak.resource.admin.models;

public class RoleDto {
    private String id;
    private String name;

    public RoleDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getRoleId() {
        return this.id;
    }

    public void setRoleId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}