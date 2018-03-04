package com.hotelbooking.data;

import com.hotelbooking.BeanMatcher;
import com.hotelbooking.model.Reservation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.TestUtil.toDate;
import static com.hotelbooking.data.ClientTestData.*;
import static com.hotelbooking.data.RoomTestData.*;
import static com.hotelbooking.model.AbstractBaseEntity.START_SEQ;

public class ReservationTestData {

    public static final Long RESERVATION1_ID = intToLong(START_SEQ+22);


    public static Reservation RESERVATION1 = new Reservation(RESERVATION1_ID, toDate("2018-05-30"),toDate("2018-06-05"),false, false);
    public static Reservation RESERVATION2 = new Reservation(RESERVATION1_ID+1,toDate("2018-07-15"),toDate("2018-07-20"),true, false);
    public static Reservation RESERVATION3 = new Reservation(RESERVATION1_ID+2,toDate("2018-06-10"),toDate("2018-06-30"),false, false);
    public static Reservation RESERVATION4 = new Reservation(RESERVATION1_ID+3,toDate("2018-08-01"),toDate("2018-08-21"),true, true);


    static{
        RESERVATION1.setClient(CLIENT1);
        RESERVATION1.setClient(CLIENT2);
        RESERVATION1.setClient(CLIENT3);
        RESERVATION1.setClient(CLIENT4);
        RESERVATION1.setRoom(ROOM1);
        RESERVATION2.setRoom(ROOM4);
        RESERVATION3.setRoom(ROOM7);
        RESERVATION4.setRoom(ROOM9);
    }


    public static final List<Reservation> RESERVATIONS = Arrays.asList(RESERVATION4, RESERVATION3, RESERVATION2, RESERVATION1);

    public static Reservation getCreated() {
        return new Reservation(null,  toDate("2020-02-20"),toDate("2020-02-25"),true, true);
    }


    public static final BeanMatcher<Reservation> MATCHER = BeanMatcher.of(Reservation.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getCheckIn(), actual.getCheckIn())
                                    && Objects.equals(expected.getCheckOut(), actual.getCheckOut())
                                    && Objects.equals(expected.getIsBreakfast(), actual.getIsBreakfast())
                                    && Objects.equals(expected.getIsCleaning(), actual.getIsCleaning())
                    )
    );
    
    
}
