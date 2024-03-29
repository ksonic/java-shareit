package ru.practicum.shareit.booking;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue
    private long id;
    private String start;
    private String end;
    private String item;
    private String booker;
    private BookingStatus status;
}
