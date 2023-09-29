package com.nado.dto;

import com.nado.model.PermissionGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionGroupDto {
    private Long id;
    private String groupName;

    private List<PermissionDto> permissions = new ArrayList<>();


    public PermissionGroupDto(PermissionGroup group) {
        this.id =group.getId();
        this.groupName = group.getGroupName();
        this.permissions = PermissionDto.mapEntitiesToDto(group.getPermissions());
    }

    public static List<PermissionGroupDto> mapEntitiesToDto(List<PermissionGroup> groups) {
        return groups.stream()
                .map(group -> new PermissionGroupDto(group)).collect(Collectors.toList());
    }


}
