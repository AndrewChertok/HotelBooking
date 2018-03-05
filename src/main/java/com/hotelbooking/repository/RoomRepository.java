package com.hotelbooking.repository;

import com.hotelbooking.model.Room;
import java.util.Date;
import java.util.List;

public interface RoomRepository {

    Room save(Room room, Long hotelId);

    void delete(Long id);

    Room get(Long id);

    List<Room> getAllByHotel(Long hotelId);

    List<Room> getUnoccupiedRooms(Long hotelId, Date checkin, Date checkout);

}
