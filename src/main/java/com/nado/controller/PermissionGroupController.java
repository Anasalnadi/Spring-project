package com.nado.controller;

import com.nado.dao.PermissionGroupRepo;
import com.nado.dto.EntityResponse;
import com.nado.dto.PermissionGroupDto;
import com.nado.model.PermissionGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/permission-group")
public class PermissionGroupController {

    private final PermissionGroupRepo repo;

    public PermissionGroupController(PermissionGroupRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<EntityResponse<List<PermissionGroupDto>>> getAllPermissionGroups() {
        List<PermissionGroup> allPermissionGroups = repo.findAll();
        return ResponseEntity.ok(new EntityResponse<>(PermissionGroupDto.mapEntitiesToDto(allPermissionGroups)));
    }
}
