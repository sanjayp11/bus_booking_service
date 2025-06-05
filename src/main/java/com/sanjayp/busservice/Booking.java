package com.sanjayp.busservice;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Getter
@Setter
@Entity
@Table(name = "bus_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @Column(name = "bus_id", nullable = false)
    private Long busId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "source", length = 30, nullable = false)
    private String source;

    @Column(name = "destination", length = 30, nullable = false)
    private String destination;

    @Column(name = "no_of_seats", nullable = false)
    private Long noOfSeats;

    @Column(name = "status", nullable = false)
    private String status;
}
