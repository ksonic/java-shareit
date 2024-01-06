package ru.practicum.shareit.user.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
public interface UserService {
    User create(User user);

    List<User> getAllUsers();

    User getById(Long id);

    User update(User user);

    void delete(Long id);
}
