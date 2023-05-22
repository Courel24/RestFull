package com.example.demo.controller;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NotifierListenerController {
    @RabbitListener(queues = {"PickupNotificationQueue"})
    public void receiveOrder(PickupPetNotificationDTO pickupPetNotificationDTO)
    {
        System.out.println("Notification related to client " + pickupPetNotificationDTO.getClientName()
                + " to announce the ending of the service, and require to pickup the pet "
                + pickupPetNotificationDTO.getPetName() + " to the email " + pickupPetNotificationDTO.getEmail());
    }
}
