package com.example.demo.service;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class PickupPetNotificationService {

    private RabbitTemplate rabbitTemplate;

    public void sendPickupPetNotification(String email, String clientName, String petName){
        PickupPetNotificationDTO pickupPetNotificationDTO = new PickupPetNotificationDTO(email, clientName, petName);
        rabbitTemplate.convertAndSend("exchange", "email", pickupPetNotificationDTO);
    }

}
