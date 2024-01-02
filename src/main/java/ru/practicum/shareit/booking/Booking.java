package ru.practicum.shareit.booking;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * TODO Sprint add-bookings.
 */
@Component
@Data
public class Booking {
    @Id
    @GeneratedValue
    private long id;
    private String start;
    private String end;
    private String item;
    private String booker;
    private Status status;
}
