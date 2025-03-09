package com.example.weather_app.service;

import com.example.weather_app.dto.MessageDTO;
import org.springframework.stereotype.Service;
@Service
public class AppService {
    public MessageDTO healthCheck() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("up");
        return messageDTO;
    }
}