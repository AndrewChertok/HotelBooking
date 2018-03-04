package com.hotelbooking.data;

import com.hotelbooking.BeanMatcher;
import com.hotelbooking.model.Hotel;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.data.ContactTestData.CONTACT1_ID;
import static com.hotelbooking.data.ContactTestData.CONTACT4;
import static com.hotelbooking.data.ContactTestData.CONTACT5;
import static com.hotelbooking.model.AbstractBaseEntity.START_SEQ;

public class HotelTestData {


    public static final Long HOTEL1_ID = intToLong(START_SEQ+6);

    public static final Hotel HOTEL1 = new Hotel(HOTEL1_ID,"The Savoy", CONTACT4);
    public static final Hotel HOTEL2 = new Hotel(HOTEL1_ID+1, "Hyatt", CONTACT5);


    public static final List<Hotel> ROOMS = Arrays.asList(HOTEL2, HOTEL1);

    public static Hotel getCreated() {
        return new Hotel(null, "New Hotel", ContactTestData.getCreated());
    }

    public static final BeanMatcher<Hotel> MATCHER = BeanMatcher.of(Hotel.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName()))
    );
}
