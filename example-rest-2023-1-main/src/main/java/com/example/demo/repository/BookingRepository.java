package com.example.demo.repository;
import com.example.demo.controller.dto.BookingDTO;
import com.example.demo.controller.dto.ClientDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Modifying
    @Query("INSERT INTO BOOKING (CLIENT_ID, PET_ID, DATE) VALUES (:clientId, :petId, :date)")
    int insertBooking(@Param("clientId") int clientId, @Param("petId") int petId, @Param("date") String date);

    @Query("SELECT * FROM BOOKING WHERE DATE = :date")
    Client getClientPets(@Param("date") String date);

}
