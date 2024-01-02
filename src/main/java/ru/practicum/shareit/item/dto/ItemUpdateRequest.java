package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemUpdateRequest {
    Boolean available;
    private long id;
    private String name;
    private String description;
    private String created;
}
