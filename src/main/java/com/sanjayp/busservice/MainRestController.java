package com.sanjayp.busservice;

import com.fasterxml.jackson.core.JsonProcessingException;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
//import io.github.resilience4j.retry.annotation.Retry;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
//import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Tag(name = "Social Service", description = "Social Service APIs")
@RestController
@RequestMapping("/api/v1")
public class MainRestController {

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    @Autowired
    BusRouteRepository busRouteRepository;

    @Autowired
    BusRoute busRoute;

    @PostMapping(value = "/admin-service/")
    public ResponseEntity<String> create(@RequestBody BusRouteView busRouteView) {

        BusRoute busRoute = new BusRoute();
        busRoute.setId(busRouteView.getId());
        busRoute.setSource(busRouteView.getSource());
        busRoute.setDestination(busRouteView.getDestination());
        busRoute.setPrice(busRouteView.getPrice());
        busRouteRepository.save(busRoute);
        return ResponseEntity.ok().body("Bus Route created successfully for id: " + busRouteView.getId());
    }

    @GetMapping("/admin-service/")
    public ResponseEntity<?> fetch() {
        return ResponseEntity.ok().body(busRouteRepository.findAll());
    }

    @GetMapping("/admin-service/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
//        LOGGER.info("Finding product by ID:"+id);
        return ResponseEntity.ok().body(busRouteRepository.findById(id));
    }



    @PutMapping("/admin-service/")
    public ResponseEntity<String> update(@RequestBody BusRouteView busRouteView) {
        // handle the case when the route is not found
        BusRoute busRoute = busRouteRepository.findById(busRouteView.getId()).orElseGet(BusRoute::new);
        busRoute.setId(busRouteView.getId());
        busRoute.setSource(busRouteView.getSource());
        busRoute.setDestination(busRouteView.getDestination());
        busRoute.setPrice(busRouteView.getPrice());
        busRouteRepository.save(busRoute);
        return ResponseEntity.ok().body("Bus Route updated successfully for id: " + busRouteView.getId());
    }

    @DeleteMapping("/admin-service/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        busRouteRepository.deleteById(id);
        return ResponseEntity.ok().body("Bus Route deleted successfully for userid: " + id);
    }


}
