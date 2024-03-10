package com.pat.keycloak.resource.admin.mappers;

import org.keycloak.models.RoleModel;

import com.pat.keycloak.resource.admin.models.RoleDto;

public class RoleMapper {
    public RoleDto mapToRoleDto(RoleModel rm) {
        return new RoleDto(
                rm.getId(),
                rm.getName());
    }
}