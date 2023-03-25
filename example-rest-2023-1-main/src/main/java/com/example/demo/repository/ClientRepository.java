package com.example.demo.repository;
import com.example.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c.DOCUMENT, c.NAME, p.ID, p.NAME FROM CLIENT c INNER JOIN PET p ON c.DOCUMENT = p.CLIENT WHERE c.DOCUMENT = :document")
    Client getClientPets(@Param("document") int document);

    @Query("SELECT c.DOCUMENT, c.NAME, b.ID FROM CLIENT c INNER JOIN BOOKING b ON c.DOCUMENT = p.CLIENT_ID WHERE c.DOCUMENT = :document")
    Client getClientBookings(@Param("document") int document);
}
