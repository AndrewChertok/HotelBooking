package com.hotelbooking.repository;

import com.hotelbooking.model.Reservation;
import java.util.List;

public interface ReservationRepository {

    Reservation save(Reservation reservation, Long clientId, Long roomId);

    void delete(Long id);

    Reservation get(Long id);

    Reservation getByClient(Long clientId);

    List<Reservation> getAllByHotel(Long hotelId);
}
