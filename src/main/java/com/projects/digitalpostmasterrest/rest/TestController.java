package com.projects.digitalpostmasterrest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/current-time")
    public void getCurrentTime(){
        String time = LocalTime.now().toString();
        System.out.println("Time is " + time);
    }

}