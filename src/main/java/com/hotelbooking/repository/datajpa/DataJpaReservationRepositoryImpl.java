package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class DataJpaReservationRepositoryImpl implements ReservationRepository {

    @Autowired
    CrudReservationRepository reservationRepository;

    @Autowired
    CrudClientRepository clientRepository;

    @Autowired
    CrudRoomRepository roomRepository;

    @Override
    public Reservation save(Reservation reservation, Long clientId, Long roomId) {
        if (!reservation.isNew() && get(reservation.getId()) == null) {
            return null;
        }
        reservation.setClient(clientRepository.getOne(clientId));
        reservation.setRoom(roomRepository.getOne(roomId));
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public Reservation get(Long id) {
        return reservationRepository.getOne(id);
    }

    @Override
    public List<Reservation> getAllByHotel(Long hotelId) {
        return reservationRepository.getAllByHotel(hotelId);
    }

    @Override
    public Reservation getByClient(Long clientId) {
        return reservationRepository.getByClient(clientId);
    }

}
