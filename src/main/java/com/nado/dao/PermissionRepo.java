package com.nado.dao;

import com.nado.model.Permission;
import com.nado.model.PermissionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long> {

    public Optional<Permission> findByUserEmailAndPermissionLevel(String userEmail, PermissionLevel permissionLevel);

    public Optional<Permission> findByPermissionGroupIdAndPermissionLevel(Long permissionGroupId, PermissionLevel permissionLevel);
}
