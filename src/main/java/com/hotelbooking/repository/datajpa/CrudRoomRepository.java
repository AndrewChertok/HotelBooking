package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRoomRepository extends JpaRepository<Room, Long> {

    @Override
    Room getOne(Long id);

    @Override
    Room save(Room room);

    @Override
    void delete(Long id);

    @Query("SELECT r FROM Room r WHERE r.hotel.id=:hotelId ORDER BY r.number")
    List<Room> getAllByHotel(@Param("hotelId") Long hotelId);

    //TODO check correction
    @Query("SELECT r FROM Room r WHERE r NOT IN (SELECT res.room FROM Reservation res WHERE (res.checkInDate >= :checkin AND res.checkOutDate <= :checkout)) AND r.hotel.id=:hotelId ORDER BY r.roomType")
    List<Room> getUnoccupiedRooms(@Param("hotelId") Long hotelId,@Param("checkin") Date checkin,@Param("checkout") Date checkout);


}
