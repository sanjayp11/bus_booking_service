package com.sanjayp.busservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id", nullable = false)
    private Long id;

    @Column(name = "booking_id", nullable = false)
    private Long bookingId;
}
