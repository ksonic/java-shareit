package ru.practicum.shareit.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponse {
    Boolean available;
    private long id;
    private String name;
    private String description;
    private Long owner;
}
