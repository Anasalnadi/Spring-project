package com.nado.dao;

import com.nado.dto.FileDto;
import com.nado.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File,Long> {
    @Query(value=""
            + "SELECT i.TYPE AS itemType,i.name AS fileName,i2.name as folderName, "
            + "COALESCE(i3.name, i2.name) AS spaceName "
            + "FROM files f "
            + "JOIN items i ON i.id = f.item_id "
            + "JOIN items i2 ON i2.id = i.parent_item_id "
            + "LEFT JOIN items i3 ON i3.id = i2.parent_item_id "
            + "WHERE f.id = :fileId"
            , nativeQuery=true)
    Optional<FileDto> getFileMetaData(Long fileId);
}
