package com.hotelbooking.repository;

import com.hotelbooking.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository {

    Reservation save(Reservation reservation, Long clientId, Long roomId);

    void delete(Long id);

    Reservation get(Long id);

    Reservation getByClientId(Long clientId);

    List<Reservation> getAllByHotel(Long hotelId);

    List<Reservation> getBetweenDates(Long hotelId, Date checkin, Date checkout);
}
