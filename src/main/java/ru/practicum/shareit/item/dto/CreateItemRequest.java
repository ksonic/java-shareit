package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class CreateItemRequest {
    @NotNull
    private Boolean available;
    private long id;
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    @NotBlank
    @Size(max = 200, message = "Description should be less than 200 symbols")
    private String description;
    private String created;
}
