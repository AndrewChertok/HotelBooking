package com.hotelbooking.web.hotel;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;
import com.hotelbooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class HotelAbstractController{

    @Autowired
    HotelService hotelService;

    List<Room> getAvailableRooms(Long hotelId, Date checkin, Date checkout){
        return hotelService.getAvailableRooms(hotelId, checkin, checkout);
    }

    List<Room> filterRoomsByCategory(Long hotelId, RoomType roomType){
      return hotelService.filterRoomsByCategory(hotelId, roomType);
    }

    List<Reservation> getReservations(Long hotelId){
      return hotelService.getReservations(hotelId);
    }
}
