package com.nado.dao;

import com.nado.model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionGroupRepo extends JpaRepository<PermissionGroup, Long> {
}
