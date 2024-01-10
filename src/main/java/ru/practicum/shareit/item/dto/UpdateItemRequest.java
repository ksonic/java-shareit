package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateItemRequest {
    private Boolean available;
    private long id;
    private String name;
    private String description;
    private String created;
}
