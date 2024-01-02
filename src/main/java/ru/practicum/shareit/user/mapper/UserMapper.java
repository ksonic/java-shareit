package ru.practicum.shareit.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.shareit.user.dto.UserCreateRequest;
import ru.practicum.shareit.user.dto.UserResponse;
import ru.practicum.shareit.user.dto.UserUpdateRequest;
import ru.practicum.shareit.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name")
    User toUser(UserCreateRequest request);

    @Mapping(target = "id", source = "id")
    User toUser(UserUpdateRequest request, Long id);

    UserResponse toResponse(User user);
}
