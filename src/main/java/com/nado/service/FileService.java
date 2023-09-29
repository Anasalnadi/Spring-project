package com.nado.service;

import com.nado.dao.FileRepo;
import com.nado.dao.PermissionRepo;
import com.nado.dto.FileDto;
import com.nado.model.File;
import com.nado.model.Permission;
import com.nado.model.PermissionLevel;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileService {

    private static final Logger logger = Logger.getLogger(FileService.class.getName());

    private final FileRepo fileRepository;

    private final PermissionRepo permissionRepository;

    public FileService(FileRepo fileRpository, PermissionRepo permissionRepository) {
        this.fileRepository = fileRpository;
        this.permissionRepository = permissionRepository;
    }


    public File save(File file) {
        return fileRepository.save(file);
    }


    public byte[] downloadFile(File file) {
        Optional<File> dbFile = fileRepository.findById(file.getId());
        if(!dbFile.isPresent()) {
            logger.log(Level.WARNING, "File Not Found");
        }

        Long permissoinID = dbFile.get().getItem().getPermissionGroup().getId();
        Optional<Permission> userPermission = permissionRepository.findByPermissionGroupIdAndPermissionLevel(permissoinID, PermissionLevel.DOWNLOAD);
        if (!userPermission.isPresent()) {
            logger.log(Level.WARNING, "you do not have a permisson to download this file!");
        }

        return dbFile.get().getBinary();
    }


    public FileDto getFileMetaData(Long fileId) {
        Optional<FileDto> fileMetaData = fileRepository.getFileMetaData(fileId);
        if (!fileMetaData.isPresent()) {
            logger.log(Level.WARNING, "File Not Found");
        }

        return fileMetaData.get();
    }
}
