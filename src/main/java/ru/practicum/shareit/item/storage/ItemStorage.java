package ru.practicum.shareit.item.storage;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemStorage {
    Item create(Item item);

    Item getById(Long id);

    Item update(Item item);

    List<Item> getAll(Long userId);

    List<Item> getSearchResults(String query);

    boolean checkOwner(Item item);

    boolean checkItems(Item item);
}
