package com.example.demo.repository;
import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public interface DbHandler {
    void insertClient(ClientDTO client);
    void insertBooking(BookingDTO booking);
    ArrayList<String> findPetsByDate(String date);
    List<BookingDTO> obtainClientBookingHistory(String id);
    int obtainBookingAmount(BookingDTO booking);
}
