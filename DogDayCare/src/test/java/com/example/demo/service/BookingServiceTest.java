package com.example.demo.service;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Client;
import com.example.demo.entity.Pet;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private BookingService bookingService;


    @Test
    void Given_more_than_20_bookings_When_invoke_insertBooking_Then_return_limit_message() {

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setDate(new Date());
        Booking probe = new Booking();
        probe.setDate(bookingDTO.getDate());
        bookingDTO.setClient_id(1234);
        Mockito.when(clientRepository.getReferenceById(bookingDTO.getClient_id())).thenReturn(new Client());
        probe.setClient(clientRepository.getReferenceById(bookingDTO.getClient_id()));

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "client_id", "pet_id");
        Mockito.when(bookingRepository.count(Example.of(probe, matcher))).thenReturn(21L);
        String result = bookingService.insertBooking(bookingDTO);

        Assertions.assertEquals("booking cannot be added", result);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(bookingDTO.getClient_id());

        Mockito.verify(bookingRepository).count(Example.of(probe, matcher));
    }

    @MockitoSettings(strictness = Strictness.LENIENT)
    @Test
    void Given_less_than_20_bookings_and_client_does_not_have_a_booking_When_invoke_insertBooking_Then_return_success_message() {

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setDate(new Date());
        bookingDTO.setClient_id(123);
        bookingDTO.setPet_id(1234);
        Mockito.when(clientRepository.getReferenceById(bookingDTO.getClient_id())).thenReturn(new Client());

        Booking probe = new Booking();
        probe.setDate(bookingDTO.getDate());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "client_id", "pet_id");
        Mockito.when(bookingRepository.count(Example.of(probe, matcher))).thenReturn(19L);

        probe.setClient(clientRepository.getReferenceById(bookingDTO.getClient_id()));
        matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id", "date");
        Mockito.when(bookingRepository.count(Example.of(probe, matcher))).thenReturn(0L);
        Mockito.when(petRepository.getReferenceById(bookingDTO.getPet_id())).thenReturn(new Pet());

        Mockito.when(bookingRepository.save(new Booking(bookingDTO.getId(), clientRepository.getReferenceById(bookingDTO.getClient_id()), petRepository.getReferenceById(bookingDTO.getPet_id()), bookingDTO.getDate()))).thenReturn(new Booking());

        String result = bookingService.insertBooking(bookingDTO);
        Assertions.assertEquals("booking added", result);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(bookingDTO.getClient_id());
        Mockito.verify(bookingRepository).count(Example.of(probe, matcher));
        Mockito.verify(petRepository, Mockito.atLeastOnce()).getReferenceById(bookingDTO.getPet_id());
        Mockito.verify(bookingRepository).save(isA(Booking.class));

    }

    @Test
    void Given_less_than_20_bookings_and_client_have_a_booking_When_invoke_insertBooking_Then_return_full_booking_message() {

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setDate(new Date());
        bookingDTO.setClient_id(123);
        Mockito.when(clientRepository.getReferenceById(bookingDTO.getClient_id())).thenReturn(new Client());

        Booking probe = new Booking();
        probe.setDate(bookingDTO.getDate());
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "client_id", "pet_id");
        Mockito.when(bookingRepository.count(Example.of(probe, matcher))).thenReturn(19L);

        probe.setClient(clientRepository.getReferenceById(bookingDTO.getClient_id()));
        matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id", "date");
        Mockito.when(bookingRepository.count(Example.of(probe, matcher))).thenReturn(1L);

        String result = bookingService.insertBooking(bookingDTO);
        Assertions.assertEquals("you already have a booking for another pet", result);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(bookingDTO.getClient_id());
        Mockito.verify(bookingRepository).count(Example.of(probe, matcher));

    }

    @Test
    void Given_clientID_When_invoke_getClientBookingHistory_Then_return_booking_history() {
        Booking probe = new Booking();
        int clientDocument = 123;
        Mockito.when(clientRepository.getReferenceById(clientDocument)).thenReturn(new Client());
        probe.setClient(clientRepository.getReferenceById(clientDocument));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id", "date");
        List<Booking> historyList = new ArrayList<>();
        historyList.add(probe);
        Mockito.when(bookingRepository.findAll(Example.of(probe, matcher))).thenReturn(historyList);
        List result = bookingService.getClientBookingHistory(clientDocument);
        Assertions.assertEquals(historyList, result);
        Mockito.verify(bookingRepository).findAll(Example.of(probe, matcher));
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).getReferenceById(clientDocument);
    }


}
