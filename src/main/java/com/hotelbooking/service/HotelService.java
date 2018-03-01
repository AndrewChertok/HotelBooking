package com.hotelbooking.service;

import com.hotelbooking.model.Room;

import java.util.List;

public interface HotelService {

    List<Room> getAvailableRooms();

    List<Room> filterRoomsByCategory();

    List<Room> getReservations();
}
