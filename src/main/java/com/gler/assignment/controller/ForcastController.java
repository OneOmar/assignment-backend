package com.gler.assignment.controller;

import com.gler.assignment.dto.ForcastRequest;
import com.gler.assignment.service.ForcastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ForcastController {

    private final ForcastService forcastService;

    public ForcastController(ForcastService forcastService) {
        this.forcastService = forcastService;
    }

    @PostMapping("/forcast")
    public ResponseEntity<?> getForecast(@RequestBody ForcastRequest request) {

        if (request.getAddTemperature() == null ||
                request.getAddHumidity() == null ||
                request.getAddWindSpeed() == null) {

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(forcastService.getForecast(request));
    }
}