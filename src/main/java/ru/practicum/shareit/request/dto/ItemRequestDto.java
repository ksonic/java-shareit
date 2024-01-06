package ru.practicum.shareit.request.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ItemRequestDto {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    @Size(max = 200, message = "Description should be less than 200 symbols")
    private String description;
    private String requestor;
    private String created;
}
