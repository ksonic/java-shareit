package ru.practicum.shareit.item.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.item.dto.CreateItemRequest;
import ru.practicum.shareit.item.dto.ItemResponse;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemResponse toResponse(Item item);

    @Mapping(target = "owner", source = "userId")
    Item toItem(CreateItemRequest request, Long userId);

    @Mapping(target = "owner", source = "userId")
    @Mapping(target = "id", source = "itemId")
    Item toItem(UpdateItemRequest request, Long itemId, Long userId);
}
