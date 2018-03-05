package com.hotelbooking.repository.datajpa;

import com.hotelbooking.data.RoomTestData;
import com.hotelbooking.model.Room;
import com.hotelbooking.repository.AbstractRepositoryTest;
import com.hotelbooking.repository.HotelRepository;
import com.hotelbooking.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.hotelbooking.TestUtil.toDate;
import static com.hotelbooking.data.HotelTestData.HOTEL1;
import static com.hotelbooking.data.HotelTestData.HOTEL1_ID;
import static com.hotelbooking.data.RoomTestData.*;



public class RoomRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void save() throws Exception {
        Room newRoom = RoomTestData.getCreated();
        newRoom.setHotel(HOTEL1);
        Room savedRoom = roomRepository.save(newRoom, HOTEL1_ID);
        MATCHER.assertEquals(newRoom, savedRoom);
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void delete() throws Exception {
        roomRepository.delete(ROOM1_ID);
        Room room = roomRepository.get(ROOM1_ID);
    }

    @Test
    public void get() throws Exception {
        Room room = roomRepository.get(ROOM1_ID);
        MATCHER.assertEquals(ROOM1, room);
    }

    @Test
    public void getAllByHotel() throws Exception {
        List<Room> list = roomRepository.getAllByHotel(HOTEL1_ID);
        list.sort(Comparator.comparing(Room::getRoomType));
        MATCHER.assertListEquals(Arrays.asList(ROOM1, ROOM2, ROOM3, ROOM4, ROOM5), list);
    }

    @Test
    public void getUnoccupiedRooms() throws Exception {
        List<Room> list = roomRepository.getUnoccupiedRooms(HOTEL1_ID, toDate("2018-07-16"), toDate("2018-07-19"));
        list.sort(Comparator.comparing(Room::getRoomType));
        MATCHER.assertListEquals(Arrays.asList(ROOM1, ROOM2, ROOM3, ROOM5), list);
    }

}