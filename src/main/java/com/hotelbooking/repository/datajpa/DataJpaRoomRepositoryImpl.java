package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Room;
import com.hotelbooking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public class DataJpaRoomRepositoryImpl implements RoomRepository {


    @Autowired
    CrudRoomRepository roomRepository;

    @Autowired
    CrudHotelRepository hotelRepository;

    @Override
    public Room save(Room room, Long hotelId) {
        if (!room.isNew() && get(room.getId()) == null) {
            return null;
        }
        room.setHotel(hotelRepository.findOne(hotelId));
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.delete(id);
    }

    @Override
    public Room get(Long id) {
        return roomRepository.getOne(id);
    }

    @Override
    public List<Room> getAllByHotel(Long hotelId) {
        return roomRepository.getAllByHotel(hotelId);
    }


    @Override
    public List<Room> getUnoccupiedRooms(Long hotelId, Date checkin, Date checkout) {
        return roomRepository.getUnoccupiedRooms(hotelId, checkin, checkout);
    }

}
