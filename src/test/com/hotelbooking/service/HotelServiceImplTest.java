package com.hotelbooking.service;

import com.hotelbooking.data.ReservationTestData;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static com.hotelbooking.TestUtil.toDate;
import static com.hotelbooking.data.HotelTestData.HOTEL1_ID;
import static com.hotelbooking.data.ReservationTestData.RESERVATION1;
import static com.hotelbooking.data.ReservationTestData.RESERVATION2;
import static com.hotelbooking.data.RoomTestData.*;


public class HotelServiceImplTest extends AbstractServiceTest{

    @Autowired
    HotelService hotelService;

    @Test
    public void filterRoomsByCategory() throws Exception {
      List<Room> list = hotelService.filterRoomsByCategory(HOTEL1_ID, RoomType.ROYAL);
        MATCHER.assertListEquals(Arrays.asList(ROOM5), list);
    }

    @Test
    public void getReservations() throws Exception {
        List<Reservation> list = hotelService.getReservations(HOTEL1_ID);
        ReservationTestData.MATCHER.assertListEquals(Arrays.asList(RESERVATION1, RESERVATION2), list);
    }

    @Test
    public void getAvailableRooms() throws Exception {
        List<Room> list = hotelService.getAvailableRooms(HOTEL1_ID, toDate("2018-07-16"), toDate("2018-07-19"));
        list.sort(Comparator.comparing(Room::getRoomType));
        MATCHER.assertListEquals(Arrays.asList(ROOM1, ROOM2, ROOM3, ROOM5), list);
    }

}