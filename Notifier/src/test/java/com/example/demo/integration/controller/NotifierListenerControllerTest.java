package com.example.demo.integration.controller;

import com.example.demo.AbstractTest;
import com.example.demo.controller.NotifierListenerController;
import com.example.demo.controller.dto.PickupPetNotificationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@ActiveProfiles("test")
public class NotifierListenerControllerTest extends AbstractTest {

    @Autowired
    private NotifierListenerController notifierListenerController;

    @Test
    public void Given_pickupPetNotificationJson_When_invoke_receiveOrder_Then_return_successful_message() throws IOException {
        PickupPetNotificationDTO pickupPetNotificationDTO = new PickupPetNotificationDTO("angelgosa@unisabana.edu.co", "Angel", "copito");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(pickupPetNotificationDTO);
        String result = notifierListenerController.receiveOrder(json);
        Assertions.assertEquals("message sent successfully", result);
    }

}
