package com.sanjayp.busservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BusServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BusServiceApplication.class, args);
    }

}
