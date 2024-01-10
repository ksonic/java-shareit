package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateUserRequest {
    private final long id;
    private final String name;
    private final String email;
}
