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
    @Transactional
    Room save(Room room);

    @Override
    @Transactional
    void delete(Long id);

    @Query("SELECT r FROM Room r WHERE r.hotel.id=:id ORDER BY r.number")
    List<Room> getAllByHotel(@Param("id") Long hotelId);


    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT res.room.id FROM Reservation res WHERE (res.checkIn>=:inn AND res.checkIn<:out) OR (res.checkIn<=:inn AND res.checkOut>=:out) OR (res.checkOut>=:inn AND res.checkOut<:out)) AND r.hotel.id=:id ORDER BY r.roomType")
    List<Room> getUnoccupiedRooms(@Param("id") Long hotelId, @Param("inn") Date checkin, @Param("out") Date checkout);

}
