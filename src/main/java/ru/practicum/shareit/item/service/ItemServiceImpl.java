package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemStorage;
import ru.practicum.shareit.user.exception.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemStorage storage;

    @Autowired
    public ItemServiceImpl(ItemStorage itemStorage) {
        this.storage = itemStorage;
    }

    @Override
    public Item create(Item item) {
        if (!storage.checkOwner(item)) {
            throw new DataNotFoundException("Owner " + item.getOwner() + " is not found in users.");
        }
        return storage.create(item);
    }

    @Override
    public List<Item> getItems(long userId) {
        return new ArrayList<>(storage.getAll(userId));
    }

    @Override
    public Item getById(Long id) {
        return storage.getById(id);
    }

    @Override
    public Item update(Item item) {
        if (!storage.checkItems(item)) {
            throw new DataNotFoundException("Item " + item.getOwner() + " is not found for current user.");
        }
        return storage.update(item);
    }

    @Override
    public List<Item> getSearchResults(String query) {
        if (query.isEmpty()) {
            return new ArrayList<>();
        }
        return storage.getSearchResults(query);
    }
}
