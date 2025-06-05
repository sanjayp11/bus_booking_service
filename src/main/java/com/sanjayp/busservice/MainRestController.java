package com.sanjayp.busservice;

//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.retry.annotation.Retry;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
//import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.client.WebClient;
//@Tag(name = "Social Service", description = "Social Service APIs")
@RestController
@RequestMapping("/api/v1")
public class MainRestController {

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    Booking booking;

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private MessageSender messageSender;

    @PostMapping(value = "/bus-booking-service/")
    public ResponseEntity<String> create(@RequestBody BookingView bookingView) {

        WebClient authValidateWebClient = ctx.getBean("inventoryValidateWebClient", WebClient.class);

//        log.info("Calling auth-service to validate token: " + token);
        // forward a request to the auth service for validation
        String authResponse = authValidateWebClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Thread is Blocked until the response is received | SYNC

        log.info("Response from auth-service: " + authResponse);
        String finalMsg = "Requested seats not available";
        if(authResponse != null) {
            Long currentInventory = Long.parseLong(authResponse);
            if (bookingView.getNoOfSeats() <= currentInventory) {
                Booking booking = new Booking();
                booking.setBusId(bookingView.getBusId());
                booking.setBookingDate(bookingView.getBookingDate());
                booking.setSource(bookingView.getSource());
                booking.setDestination(bookingView.getDestination());
                booking.setStatus("PENDING");
                booking.setNoOfSeats(bookingView.getNoOfSeats());
                Booking bookingCreated = bookingRepository.save(booking);


                finalMsg = "Bus Booking created successfully for id: " + bookingCreated.getId();
                String msg = "{\n" +
                        "    \"bookingId\":"+bookingCreated.getId()+",\n" +
                        "    \"paymentDate\":\""+bookingView.getBookingDate()+"\"\n" +
                        "}";
                messageSender.sendToQueue(msg);
            }
        }

        return ResponseEntity.ok().body(finalMsg);
    }

    @GetMapping("/bus-booking-service/")
    public ResponseEntity<?> fetch() {
        return ResponseEntity.ok().body(bookingRepository.findAll());
    }

    @GetMapping("/bus-booking-service/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(bookingRepository.findById(id));
    }

    @PutMapping("/bus-booking-service/")
    public ResponseEntity<String> update(@RequestBody BookingView bookingView) {
        // handle the case when the route is not found
        Booking booking = bookingRepository.findById(bookingView.getId()).orElseGet(Booking::new);
        booking.setBookingDate(bookingView.getBookingDate());
        booking.setSource(bookingView.getSource());
        booking.setDestination(bookingView.getDestination());
        booking.setStatus(bookingView.getStatus());
        booking.setNoOfSeats(bookingView.getNoOfSeats());
        bookingRepository.save(booking);
        return ResponseEntity.ok().body("Bus Booking edited successfully for id: " + bookingView.getId());
    }

    @DeleteMapping("/bus-booking-service/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        bookingRepository.deleteById(id);
        return ResponseEntity.ok().body("Bus Booking deleted successfully for userid: " + id);
    }


}
