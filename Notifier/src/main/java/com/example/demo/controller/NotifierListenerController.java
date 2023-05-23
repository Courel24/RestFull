package com.example.demo.controller;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
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
    @RabbitListener(queues = {"PickupNotificationQueue"})
    public void receiveOrder(String pickupPetNotificationJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        PickupPetNotificationDTO pickupPetNotificationDTO = mapper.readValue(pickupPetNotificationJson, PickupPetNotificationDTO.class);
        System.out.println("Notification related to client " + pickupPetNotificationDTO.getClientName()
                + " to announce the ending of the service, and require to pickup the pet "
                + pickupPetNotificationDTO.getPetName() + " to the email " + pickupPetNotificationDTO.getEmail());
    }
}
