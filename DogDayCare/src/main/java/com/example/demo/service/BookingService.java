package com.example.demo.service;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.PickupPetNotificationDTO;
import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingService {
    private BookingRepository bookingRepository;
    private PickupPetNotificationService pickupPetNotificationService;
    private ClientRepository clientRepository;
    private PetRepository petRepository;

    public String insertBooking(BookingDTO bookingDTO) {
        Booking probe = new Booking();
        probe.setDate(bookingDTO.getDate());
        probe.setClient(clientRepository.getReferenceById(bookingDTO.getClient_id()));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "client_id", "pet_id");
        if (bookingRepository.count(Example.of(probe, matcher)) < 20) {
            matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id");
            if (bookingRepository.count(Example.of(probe, matcher)) < 1) {
                bookingRepository.save(new Booking(bookingDTO.getId(), clientRepository.getReferenceById(bookingDTO.getClient_id()), petRepository.getReferenceById(bookingDTO.getPet_id()), bookingDTO.getDate()));
                return "booking added";
            } else {
                return "you already have a booking for another pet";
            }
        } else {
            return "booking cannot be added";
        }
    }

    public List<Booking> getClientBookingHistory(int clientDocument) {
        Booking probe = new Booking();
        probe.setClient(clientRepository.getReferenceById(clientDocument));
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "pet_id", "date");

        return bookingRepository.findAll(Example.of(probe, matcher));
    }

    public String deleteBookingByPetIdAndDate(int petId, String date){
        Booking probe = new Booking();
        probe.setPet(petRepository.getReferenceById(petId));
        probe.setDate(date);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnorePaths("id", "client");
        Optional<Booking> booking = bookingRepository.findOne(Example.of(probe, matcher));
        if (booking.isPresent()) {
            bookingRepository.delete(booking.get());
            pickupPetNotificationService.sendPickupPetNotification(booking.get().getClient().getEmail(), booking.get().getClient().getName(), booking.get().getPet().getName());
            return "Booking deleted successfully";
        } else {
            return "Booking cannot be deleted";
        }
    }
}

