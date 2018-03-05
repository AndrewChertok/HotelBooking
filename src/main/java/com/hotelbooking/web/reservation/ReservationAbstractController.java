package com.hotelbooking.web.reservation;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ReservationAbstractController {

    @Autowired
    ReservationService reservationService;


    Client create(Contact contact, String firstName, String lastName){
       return reservationService.create(contact, firstName, lastName);
    }

    Reservation bookRoom(Reservation reservation, String clientEmail, Long roomId){
        return reservationService.bookRoom(reservation, clientEmail, roomId);
    }

    Reservation getByClient(String clientEmail){
        return reservationService.getByClient(clientEmail);
    }

    int getTotal(String clientEmail){
      return  reservationService.getTotal(clientEmail);
    }
}
