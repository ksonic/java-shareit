package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {
    private final long id;
    private final String name;

    @Email
    private final String email;
}
