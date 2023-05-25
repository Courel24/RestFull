package com.example.demo.service;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@AllArgsConstructor
public class PickupPetNotificationService {

    private RabbitTemplate rabbitTemplate;

    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public void sendPickupPetNotification(String email, String clientName, String petName){
        PickupPetNotificationDTO pickupPetNotificationDTO = new PickupPetNotificationDTO(email, clientName, petName);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.convertAndSend("PickupNotificationManager", "NotificationJson", pickupPetNotificationDTO);
    }

}
