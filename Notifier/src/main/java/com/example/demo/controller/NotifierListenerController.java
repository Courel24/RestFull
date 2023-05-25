package com.example.demo.controller;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
import com.example.demo.service.NotifierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.DataInput;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Component
public class NotifierListenerController {

    private NotifierService notifierService;

    @RabbitListener(queues = {"PickupNotificationQueue"})
    public void receiveOrder(String pickupPetNotificationJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PickupPetNotificationDTO pickupPetNotificationDTO = mapper.readValue(pickupPetNotificationJson, PickupPetNotificationDTO.class);
        System.out.println(notifierService.sendNotificationToClient(pickupPetNotificationDTO));
    }
}
