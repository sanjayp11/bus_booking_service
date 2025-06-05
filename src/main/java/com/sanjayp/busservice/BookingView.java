package com.sanjayp.busservice;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookingView {
    private Long id;

    private Long busId;

    private LocalDate bookingDate;

    private String source;

    private String destination;

    private Long noOfSeats;

    private String status;
}
