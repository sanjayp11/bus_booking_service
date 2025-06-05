package com.sanjayp.busservice;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
//import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//import org.springframework.retry.annotation.Backoff;
//import org.springframework.retry.annotation.Retryable;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class AppConfig {

    //@Value("${gateway-server.hostname}")
    //String gateway_hostname;

    //@Value("${gateway-server.portnumber}")
    //String gateway_portnumber;

    @Bean
    @Scope("prototype")
    public WebClient inventoryValidateWebClient(WebClient.Builder webClientBuilder)
    {
//        List<ServiceInstance> instances = discoveryClient.getInstances("auth-service");
        //No load balancing algorithm is used here, so we are just taking the first instance
        // you can use load balancing algorithm like round robin or random if you want
        String hostname = "localhost";//instances.get(0).getHost();
        String port = "8091";//String.valueOf(instances.get(0).getPort());

        return webClientBuilder
                .baseUrl(String.format("http://%s:%s/api/v1/bus-inventory-service/260", hostname, port))
                .filter(new LoggingWebClientFilter())
                .build();
    }



}
