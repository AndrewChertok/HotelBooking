package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudHotelRepository extends JpaRepository<Hotel, Long> {

    @Override
    Hotel findOne(Long id);

    @Override
    Hotel save(Hotel hotel);

    @Override
    void delete(Long id);

    @Query("SELECT r.hotel FROM Room r WHERE r.id=:roomId")
    Hotel getByRoomId(@Param("roomId")Long roomId);
}
