package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.shareit.item.dto.CreateItemRequest;
import ru.practicum.shareit.item.dto.ItemResponse;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService service;
    private final ItemMapper mapper;
    private static final String USER_ID_HEADER = "X-Sharer-User-Id";

    @PostMapping
    public ItemResponse create(@Valid @RequestBody CreateItemRequest request, @RequestHeader(USER_ID_HEADER) Long userId) {
        Item item = mapper.toItem(request, userId);
        Item modified = service.create(item);
        return mapper.toResponse(modified);
    }

    @GetMapping("/{itemId}")
    public Item get(@PathVariable long itemId) {
        return service.getById(itemId);
    }

    @GetMapping
    public List<Item> getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        return service.getItems(userId);
    }

    @PatchMapping("/{itemId}")
    public ItemResponse update(@Valid @RequestBody UpdateItemRequest request,
                               @Valid @PathVariable Long itemId,
                               @RequestHeader(USER_ID_HEADER) Long userId) {
        Item item = mapper.toItem(request, itemId, userId);
        Item modified = service.update(item);
        return mapper.toResponse(modified);
    }

    @GetMapping("/search")
    public List<Item> getSearchResults(@RequestParam(name = "text", defaultValue = "") String text) {
        return service.getSearchResults(text);
    }
}
