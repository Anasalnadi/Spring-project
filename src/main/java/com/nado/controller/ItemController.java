package com.nado.controller;

import com.nado.dto.EntityResponse;
import com.nado.dto.ItemDto;
import com.nado.model.Item;
import com.nado.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<EntityResponse<ItemDto>> save(@RequestBody Item item) {
        Item savedItem = null;
        try {
            savedItem = itemService.save(item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ItemDto mapEntityToDto = ItemDto.mapEntityToDto(savedItem);
        return ResponseEntity.ok(new EntityResponse<>(mapEntityToDto));
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<EntityResponse<Item>> getFileMetaData(@PathVariable Long id) {

        Item fileMetaData = itemService.getFileMetaData(id);

        ItemDto fileDto = ItemDto.mapEntityToDto(fileMetaData);

        return ResponseEntity.ok(new EntityResponse<>(null));
    }
}
