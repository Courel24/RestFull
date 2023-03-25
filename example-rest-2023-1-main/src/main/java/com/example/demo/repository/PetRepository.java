package com.example.demo.repository;
import com.example.demo.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
   /* @Modifying
    @Query("INSERT INTO PET (NAME, DATE_CREATED, CLIENT) VALUES (:name, :dateCreated, :client)")
    int insertPet(@Param("name") String name, @Param("dateCreated") String dateCreated, @Param("client") int client);
    */
}
