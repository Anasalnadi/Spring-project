package com.nado.service;

import com.nado.dao.ItemRepo;
import com.nado.dao.PermissionGroupRepo;
import com.nado.dao.PermissionRepo;
import com.nado.model.*;

import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemService {

    final private ItemRepo itemRespository;
    private final PermissionGroupRepo permissionGroupRepository;
    private final FileService fileService;
    private final PermissionRepo permissionRepository;
    private static final Logger logger = Logger.getLogger(FileService.class.getName());

    public ItemService(ItemRepo itemRespository, PermissionGroupRepo permissionGroupRepository, FileService fileServiceImpl, PermissionRepo permissionRepository) {
        this.itemRespository = itemRespository;
        this.permissionGroupRepository = permissionGroupRepository;
        this.fileService = fileServiceImpl;
        this.permissionRepository = permissionRepository;
    }

    public Item save(Item item) throws Exception {
        // validation
        Optional<PermissionGroup> permissionGroup = permissionGroupRepository.findById(item.getPermissionGroup().getId());
        if(!permissionGroup.isPresent()) {
            logger.log(Level.WARNING, "Permission Group Not Found !");
        }
        item.setPermissionGroup(permissionGroup.get());

        ItemType type = item.getType();

        switch(type) {
            case SPACE:
                return saveSpaceItem(item);
            case FOLDER:
                return saveFolderItem(item);
            case FILE:
                return saveFileItem(item);
            default:
                throw new Exception("Item Type In Valid!");
        }

    }


    private Item saveSpaceItem(Item item) {
        item.setiItemParent(null);
        return itemRespository.save(item);
    }

    private Item saveFolderItem(Item item) {

        if (item.getiItemParent() == null) {
            logger.log(Level.WARNING, "Parent Item Is required To Folder Type");
        }

        Optional<Item> parentItem = itemRespository.findById(item.getiItemParent().getId());
        if (!parentItem.isPresent()) {
            logger.log(Level.WARNING, "Invalid Parent Item");
        }

        Optional<Permission> userPermission = permissionRepository.findByPermissionGroupIdAndPermissionLevel(parentItem.get().getPermissionGroup().getId(), PermissionLevel.EDIT);
        if (!userPermission.isPresent()) {
            logger.log(Level.WARNING, "you do not have a permisson to create folder in side this space");
        }

        item.setiItemParent(parentItem.get());

        return itemRespository.save(item);
    }


    private Item saveFileItem(Item item) {
        if (item.getiItemParent() == null) {
            logger.log(Level.WARNING, "Parent Item Is required To File Type");
        }


        Optional<Item> parentItem = itemRespository.findById(item.getiItemParent().getId());
        if (!parentItem.isPresent()) {
            logger.log(Level.WARNING, "Invalid Parent Item");
        }

        Optional<Permission> userPermission = permissionRepository.findByPermissionGroupIdAndPermissionLevel(parentItem.get().getPermissionGroup().getId(), PermissionLevel.EDIT);
        if (!userPermission.isPresent()) {
            logger.log(Level.WARNING, "you do not have a permisson to create folder in side this Folder");
        }


        item.setiItemParent(parentItem.get());

        if (item.getBase64File() == null) {
            logger.log(Level.WARNING, "base 64 file is required to add file type");
        }

        Item savedItem = itemRespository.save(item);

        File file = new File();
        file.setBinary(Base64.getDecoder().decode(item.getBase64File()));
        file.setItem(savedItem);
        fileService.save(file);

        item.setBase64File(null);
        return item;
    }
    public Item getFileMetaData(Long id) {
        // Use your repository to retrieve file metadata based on the id
        Optional<Item> optionalItem = itemRespository.findById(id);
        return optionalItem.orElse(null); // Return null if not found, you can handle this differently if needed
    }
}
