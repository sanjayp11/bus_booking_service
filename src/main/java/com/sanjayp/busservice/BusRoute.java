package com.sanjayp.busservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@Entity
@Table(name = "busroute")
public class BusRoute {

    @Id
    @Column(name = "bus_id", nullable = false)
    private Long id;


    @Column(name = "source", length = 30, nullable = false)
    private String source;


    @Column(name = "destination", length = 30, nullable = false)
    private String destination;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
