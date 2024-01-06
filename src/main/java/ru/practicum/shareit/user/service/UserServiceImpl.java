package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.exception.DataNotFoundException;
import ru.practicum.shareit.user.exception.DuplicateFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage storage;

    @Autowired
    public UserServiceImpl(UserStorage userStorage) {
        this.storage = userStorage;
    }

    @Override
    public User create(User user) {
        if (storage.emailExists(user.getEmail())) {
            throw new DuplicateFoundException("User with email " + user.getEmail() + " is already exists.");
        }
        return storage.create(user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.getAll());
    }

    @Override
    public User getById(Long id) {
        return storage.getById(id);
    }

    @Override
    public User update(User user) {
        if (!storage.containsUser(user.getId())) {
            throw new DataNotFoundException("User with email " + user.getId() + " is not found.");
        }
        Long id = user.getId();
        String email = user.getEmail();
        User forCheck = storage.getById(id);
        if (storage.emailExists(email) && !email.equals(forCheck.getEmail())) {
            throw new DuplicateFoundException("User with email " + user.getId() + " is already updated.");
        }
        return storage.update(user);
    }

    @Override
    public void delete(Long id) {
        storage.delete(id);
    }
}
