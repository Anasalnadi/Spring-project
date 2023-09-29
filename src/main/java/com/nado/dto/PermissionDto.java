package com.nado.dto;

import com.nado.model.Permission;
import com.nado.model.PermissionGroup;
import com.nado.model.PermissionLevel;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionDto {
    private Long id;
    private String userEmail;
    private PermissionLevel permissionLevel;
    private PermissionGroup permissionGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(PermissionLevel permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    public static List<PermissionDto> mapEntitiesToDto(List<Permission> permissions) {
        return permissions.stream()
                .map(permission -> new PermissionDto(permission)).collect(Collectors.toList());
    }


    public PermissionDto(Permission permission) {
        super();
        this.userEmail = permission.getUserEmail();
        this.permissionLevel = permission.getPermissionLevel();
        this.id = permission.getId();
    }
}
