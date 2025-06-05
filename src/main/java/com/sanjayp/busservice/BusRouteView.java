package com.sanjayp.busservice;


import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class BusRouteView {
    private Long id;

    private String source;

    private String destination;

    private BigDecimal price;
}
