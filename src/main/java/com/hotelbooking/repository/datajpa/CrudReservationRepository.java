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
    Reservation save(Reservation reservation);

    @Override
    void delete(Long id);


    @Query("SELECT res FROM Reservation res WHERE res.client.id=:clientId")
    Reservation getByClientId(@Param("clientId") Long clientId);


    @Query("SELECT res FROM Reservation res WHERE r.room.hotel=(SELECT h FROM Hotel WHERE h.id=:hotelId) AND r.checkInDate>=:checkin AND r.checkOutDate<=:checkout ORDER BY r.checkInDate")
    List<Reservation> getBetweenDates(@Param("hotelId") Long hotelId, @Param("checkin") Date checkin, @Param("checkout") Date checkout);

}
