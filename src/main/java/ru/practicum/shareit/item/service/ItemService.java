package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

@Service
public interface ItemService {
    Item create(Item item);

    List<Item> getItems(long itemId);

    Item getById(Long id);

    Item update(Item item);

    List<Item> getSearchResults(String query);
}