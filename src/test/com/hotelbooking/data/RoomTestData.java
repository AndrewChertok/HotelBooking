package com.hotelbooking.data;

import com.hotelbooking.BeanMatcher;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.TestUtil.toBigDecimal;
import static com.hotelbooking.model.AbstractBaseEntity.START_SEQ;

public class RoomTestData {


    public static final Long ROOM1_ID = intToLong(START_SEQ+8);

    
    public static final Room ROOM1 = new Room(ROOM1_ID, 11, toBigDecimal(100), RoomType.SINGLE,   toBigDecimal(30), toBigDecimal(10));
    public static final Room ROOM2 = new Room(ROOM1_ID + 1, 20,  toBigDecimal(200), RoomType.DOUBLE, toBigDecimal( 60), toBigDecimal(20));
    public static final Room ROOM3 = new Room(ROOM1_ID + 2, 3,  toBigDecimal(300), RoomType.TRIPLE, toBigDecimal( 90), toBigDecimal(30));
    public static final Room ROOM4 = new Room(ROOM1_ID + 3, 7,  toBigDecimal(500), RoomType.FAMILY, toBigDecimal( 100), toBigDecimal(40));
    public static final Room ROOM5 = new Room(ROOM1_ID + 4, 320,  toBigDecimal(1500), RoomType.ROYAL, toBigDecimal( 100), toBigDecimal(50));
    public static final Room ROOM6 = new Room(ROOM1_ID + 5,  5, toBigDecimal(100),  RoomType.SINGLE, toBigDecimal( 30), toBigDecimal(10));
    public static final Room ROOM7 = new Room(ROOM1_ID + 6, 33, toBigDecimal(200),  RoomType.DOUBLE, toBigDecimal( 60), toBigDecimal(20));
    public static final Room ROOM8 = new Room(ROOM1_ID + 7,  48,  toBigDecimal(300),  RoomType.TRIPLE, toBigDecimal( 90), toBigDecimal(30));
    public static final Room ROOM9 = new Room(ROOM1_ID + 8, 78, toBigDecimal(500), RoomType.FAMILY,  toBigDecimal( 100), toBigDecimal(40));
    public static final Room ROOM10 = new Room(ROOM1_ID + 9, 300,  toBigDecimal(1500), RoomType.ROYAL, toBigDecimal( 100), toBigDecimal(50));

    public static final List<Room> ROOMS = Arrays.asList(ROOM10, ROOM9, ROOM8, ROOM7, ROOM6, ROOM5, ROOM4, ROOM3, ROOM2, ROOM1);

    public static Room getCreated() {
        return new Room(null, 55, toBigDecimal(1000), RoomType.ROYAL, toBigDecimal(50), toBigDecimal(50));
    }


    public static final BeanMatcher<Room> MATCHER = BeanMatcher.of(Room.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getNumber(), actual.getNumber())
                                    && Objects.equals(expected.getPricePerNight(), actual.getPricePerNight())
                                    && Objects.equals(expected.getRoomType(), actual.getRoomType())
                                    && Objects.equals(expected.getBreakfast(), actual.getBreakfast())
                                    && Objects.equals(expected.getCleaning(), actual.getCleaning())
                    )
    );

}
