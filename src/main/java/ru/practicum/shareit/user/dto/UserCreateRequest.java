package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserCreateRequest {

    private final long id;
    @NotNull
    private final String name;

    @Email
    @NotNull
    private final String email;

}
