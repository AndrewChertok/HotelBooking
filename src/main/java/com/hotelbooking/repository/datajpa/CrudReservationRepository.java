package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudReservationRepository extends JpaRepository<Reservation, Long> {

    @Override
    Reservation getOne(Long id);

    @Override
    @Transactional
    Reservation save(Reservation reservation);

    @Override
    @Transactional
    void delete(Long id);

    @Query("SELECT res FROM Reservation res WHERE res.room.id IN (SELECT r.id FROM Room r WHERE r.hotel.id = :id)")
    List<Reservation> getAllByHotel(@Param("id")Long hotelId);

    @Query("SELECT res FROM Reservation res WHERE res.client.id=:id")
    Reservation getByClient(@Param("id") Long clientId);

}
