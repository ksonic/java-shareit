package ru.practicum.shareit.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.user.dto.CreateUserRequest;
import ru.practicum.shareit.user.dto.UserResponse;
import ru.practicum.shareit.user.dto.UpdateUserRequest;
import ru.practicum.shareit.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name")
    User toUser(CreateUserRequest request);

    @Mapping(target = "id", source = "id")
    User toUser(UpdateUserRequest request, Long id);

    UserResponse toResponse(User user);
}
