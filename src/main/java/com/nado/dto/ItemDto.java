package com.nado.dto;

import com.nado.model.Item;
import com.nado.model.ItemType;

public class ItemDto {
    private Long id;
    private String name;
    private ItemType type;


    public static ItemDto mapEntityToDto(Item item) {
        ItemDto dto = ItemDto.builder().id(item.getId())
                .name(item.getName())
                .type(item.getType()).build();
        return dto;
    }

    public static ItemDtoBuilder builder() {
        return new ItemDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public static class ItemDtoBuilder {
        private Long id;
        private String name;
        private ItemType type;

        private ItemDtoBuilder() {}

        public ItemDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ItemDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ItemDtoBuilder type(ItemType type) {
            this.type = type;
            return this;
        }

        public ItemDto build() {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(id);
            itemDto.setName(name);
            itemDto.setType(type);
            return itemDto;
        }
}
}
