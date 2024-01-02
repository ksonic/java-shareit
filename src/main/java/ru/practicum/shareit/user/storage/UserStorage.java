package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserStorage {

    List<User> getAll();

    User create(User user);

    User update(User user);

    User getById(Long id);

    Boolean isEmailExisted(String userEmail);

    Boolean containsUser(Long id);

    void delete(Long id);
}
