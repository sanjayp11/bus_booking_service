package com.sanjayp.busservice;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PassengerView {
    private Long id;

    private Long bookingId;
}
