package test.java.com.example.demo.integration.controller;

import test.java.com.example.demo.AbstractTest;
import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.BookingListDTO;
import com.example.demo.controller.dto.BookingResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

@ActiveProfiles("test")
public class BookingControllerTest extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String PATH_BOOKINGS_GET = "/bookings?id=";
    private static final String PATH_BOOKINGS_POST = "/bookings";

    @Test
    public void Given_clientID_When_invoke_getClientBookingHistory_Then_booking_list_size_one() {
        ResponseEntity<BookingListDTO> result = restTemplate.getForEntity(PATH_BOOKINGS_GET + 444, BookingListDTO.class);
        Assertions.assertEquals(1, result.getBody().getBookingList().size());

    }

    @Test
    public void Given_bookData_When_invoke_registerBooking_Then_success_message() {

        BookingDTO dto = new BookingDTO();
        dto.setClient_id(123);
        dto.setId(1);
        dto.setPet_id(2);
        dto.setDate(new Date());
        ResponseEntity<BookingResponseDTO> result = restTemplate.postForEntity(PATH_BOOKINGS_POST, dto, BookingResponseDTO.class);
        Assertions.assertEquals("booking added", result.getBody().getResponse());

    }

}
