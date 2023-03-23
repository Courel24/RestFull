package com.example.demo.service;

import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.repository.DbHandler;

import java.util.ArrayList;
import java.util.List;

public class DbService {
    private DbHandler handler;
    public DbService(DbHandler handler){
        this.handler = handler;
    }
    public String addClient(ClientDTO client){
        handler.insertClient(client);
        return "client added";
    }
    public String addBooking(BookingDTO booking){
        if (handler.obtainBookingAmount(booking)<20){
            handler.insertBooking(booking);
            return "booking added";
        } else {
            return "Dog DayCare Full, Try tomorrow :)";
        }

    }
    public ArrayList<String> getPetsByDate(String date){
        return handler.findPetsByDate(date);
    }
    public List<BookingDTO> getClientBookingHistory(String id){
        return handler.obtainClientBookingHistory(id);
    }

}
