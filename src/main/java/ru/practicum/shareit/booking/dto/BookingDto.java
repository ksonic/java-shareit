package ru.practicum.shareit.booking.dto;

import ru.practicum.shareit.booking.Status;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BookingDto {
    @Id
    @GeneratedValue
    private long id;
    private String start;
    private String end;
    private String item;
    private String booker;
    private Status status;
}
