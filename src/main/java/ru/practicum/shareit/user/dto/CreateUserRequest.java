package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CreateUserRequest {
    private final long id;
    @NotNull
    private final String name;

    @Email
    @NotNull
    private final String email;

}
