package com.hotelbooking.repository.datajpa;


import com.hotelbooking.model.Reservation;
import com.hotelbooking.repository.AbstractRepositoryTest;
import com.hotelbooking.repository.ClientRepository;
import com.hotelbooking.repository.ContactRepository;
import com.hotelbooking.repository.ReservationRepository;

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
import java.util.List;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.TestUtil.toDate;
import static com.hotelbooking.data.ClientTestData.CLIENT1_ID;
import static com.hotelbooking.data.ClientTestData.CLIENT2;
import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static com.hotelbooking.data.HotelTestData.HOTEL1_ID;
import static com.hotelbooking.data.ReservationTestData.*;
import static com.hotelbooking.data.RoomTestData.ROOM1;



public class ReservationRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void save() throws Exception {
        Reservation newReservation = getCreated();
        newReservation.setClient(CLIENT2);
        newReservation.setRoom(ROOM1);
        Reservation savedReservation = reservationRepository.save(newReservation, CLIENT2.getId(), ROOM1.getId());
        MATCHER.assertEquals(savedReservation, newReservation);
    }

    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void delete() throws Exception {
        reservationRepository.delete(RESERVATION1_ID);
        Reservation reservation = reservationRepository.get(RESERVATION1_ID);
    }

    @Test
    public void get() throws Exception {
       Reservation reservation = reservationRepository.get(RESERVATION1_ID);
        MATCHER.assertEquals(reservation, RESERVATION1);
    }

    @Test
    public void getAllByHotel() throws Exception {
        List<Reservation> list = reservationRepository.getAllByHotel(HOTEL1_ID);
        MATCHER.assertListEquals(Arrays.asList(RESERVATION1, RESERVATION2), list);

    }

    @Test
    public void getByClient() throws Exception {
        Reservation reservation = reservationRepository.getByClient(CLIENT1_ID);
        MATCHER.assertEquals(reservation, RESERVATION1);
    }

}