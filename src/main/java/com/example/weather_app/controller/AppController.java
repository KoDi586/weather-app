package com.example.weather_app.controller;

import com.example.weather_app.dto.MessageDTO;
import com.example.weather_app.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "system")
public class AppController {

    private final AppService appService;

    @GetMapping("/health-check")
    @Operation(summary = "Health check", description = "Check if the application is running")
    public ResponseEntity<MessageDTO> healthCheck() {
        return ResponseEntity.ok(appService.healthCheck());
    }
}