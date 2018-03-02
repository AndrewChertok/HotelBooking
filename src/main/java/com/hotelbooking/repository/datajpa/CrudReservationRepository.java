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

    //TODO delete comment
    //SELECT * FROM Rooms WHERE ROOMS.HOTEL_ID=1006 AND ROOMS.ROOM_ID NOT IN (SELECT Reservations.ROOM_ID FROM Reservations)
    @Query("SELECT r FROM Room r WHERE r.hotel.id=:hotelId AND r.id NOT IN (SELECT res.room.id FROM Reservation res)")
    List<Reservation> getAllByHotel(@Param("hotelId")Long hotelId);

    @Query("SELECT res FROM Reservation res WHERE res.client.id=:clientId")
    Reservation getByClientId(@Param("clientId") Long clientId);


    @Query("SELECT r FROM Room r WHERE r.hotel.id=:hotelId AND r.id NOT IN (SELECT res.room.id FROM Reservation res WHERE res.checkInDate>=:checkin AND res.checkOutDate<=:checkout) ORDER BY r.number")
    List<Reservation> getBetweenDates(@Param("hotelId") Long hotelId, @Param("checkin") Date checkin, @Param("checkout") Date checkout);

}
