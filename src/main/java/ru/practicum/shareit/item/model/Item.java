package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Item {
    @NotNull
    private Boolean available;
    private long id;
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    @NotBlank
    @Size(max = 200, message = "Description should be less than 200 symbols")
    private String description;
    private Long owner;
}
