package com.hotelbooking.service;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;

public interface ReservationService {

    Client create(Contact contact, String firstName, String lastName);

    Reservation bookRoom(Reservation reservation, String clientEmail, Long roomId);

    Reservation getByClient(String clientEmail);

    int getTotal(String clientEmail);

}
