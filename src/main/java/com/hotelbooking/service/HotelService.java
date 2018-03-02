package com.hotelbooking.service;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;

import java.util.Date;
import java.util.List;

public interface HotelService {

    List<Room> getAvailableRooms(Long hotelId, Date checkin, Date checkout);

    List<Room> filterRoomsByCategory(Long hotelId, RoomType roomType);

    List<Reservation> getReservations(Long hotelId);
}
