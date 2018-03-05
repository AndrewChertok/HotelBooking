package com.hotelbooking.service;

import com.hotelbooking.data.ClientTestData;
import com.hotelbooking.data.ReservationTestData;
import com.hotelbooking.data.RoomTestData;
import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static com.hotelbooking.data.ClientTestData.CLIENT1;
import static com.hotelbooking.data.ClientTestData.CLIENT2;
import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static com.hotelbooking.data.ContactTestData.getCreated;
import static com.hotelbooking.data.ReservationTestData.RESERVATION1;
import static com.hotelbooking.data.ReservationTestData.TOTAL_RESERVATION2;
import static com.hotelbooking.data.RoomTestData.ROOM1;
import static com.hotelbooking.data.RoomTestData.ROOM1_ID;
import static org.junit.Assert.*;

public class ReservationServiceImplTest extends AbstractServiceTest{


    @Autowired
    ReservationService reservationService;

    @Test
    public void create() throws Exception {
        Contact newContact = getCreated();
        Client  newClient = ClientTestData.getCreated();
       Client createdClient = reservationService.create(newContact,newClient.getFirstName(), newClient.getLastName());
        assertEquals(createdClient.getLastName(), newClient.getLastName());
    }

    @Test
    public void bookRoom() throws Exception {
        Reservation newReservation = ReservationTestData.getCreated();
        newReservation.setRoom(ROOM1);
        newReservation.setClient(CLIENT1);
       Reservation createdReservation = reservationService.bookRoom(newReservation, CONTACT1.getEmail(), ROOM1_ID);
       RoomTestData.MATCHER.assertEquals(newReservation.getRoom(), createdReservation.getRoom());
    }

    @Test
    public void getByClient() throws Exception {
        Reservation reservation = reservationService.getByClient(CONTACT1.getEmail());
        ReservationTestData.MATCHER.assertEquals(reservation, RESERVATION1);
    }

    @Test
    public void getTotal() throws Exception {
        int total = reservationService.getTotal(CLIENT2.getContact().getEmail());
        assertEquals(TOTAL_RESERVATION2, total);
    }

}