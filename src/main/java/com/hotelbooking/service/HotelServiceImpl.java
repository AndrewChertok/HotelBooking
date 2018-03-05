package com.hotelbooking.service;


import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;
import com.hotelbooking.repository.ReservationRepository;
import com.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Room> filterRoomsByCategory(Long hotelId, RoomType roomType) {
         return roomRepository.getAllByHotel(hotelId).stream().
                filter(room -> room.getRoomType() == roomType).
                collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getReservations(Long hotelId) {
        return reservationRepository.getAllByHotel(hotelId);
    }

    @Override
    public List<Room> getAvailableRooms(Long hotelId, Date checkin, Date checkout) {
        return roomRepository.getUnoccupiedRooms(hotelId, checkin, checkout);
    }
}
