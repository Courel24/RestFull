package com.example.demo.service;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Pet;
import com.example.demo.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    BookingRepository bookingRepository;

    public String insertBooking(BookingDTO bookingDTO) {
        Booking probe = new Booking();
        probe.setDate(bookingDTO.getDate());
        ExampleMatcher matcher = ExampleMatcher.matchingAny();
        System.out.println(bookingRepository.count(Example.of(probe, matcher)));
        if (bookingRepository.count(Example.of(probe, matcher)) < 20) {
            probe.setClient_id(bookingDTO.getClient_id());
            matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id");
            if(bookingRepository.count(Example.of(probe, matcher)) < 1) {
                bookingRepository.save(new Booking(bookingDTO.getId(), bookingDTO.getClient_id(), bookingDTO.getPet_id(), bookingDTO.getDate()));
                return "booking added";
            } else {
                return "you already have a booking for another pet";
            }
        } else {
            return "booking cannot be added";
        }
    }
    public List<Booking> getClientBookingHistory(int clientDocument){
        Booking probe = new Booking();
        probe.setClient_id(clientDocument);
        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        System.out.println(Example.of(probe, matcher));
        return bookingRepository.findAll(Example.of(probe, matcher));
    }
}

