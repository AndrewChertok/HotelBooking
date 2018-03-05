package com.hotelbooking.service;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.repository.ClientRepository;
import com.hotelbooking.repository.ContactRepository;
import com.hotelbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class ReservationServiceImpl implements ReservationService{

   @Autowired
   private ClientRepository clientRepository;

   @Autowired
   private ContactRepository contactRepository;

   @Autowired
   private ReservationRepository reservationRepository;

    @Override
    public Client create(Contact contact, String firstName, String lastName) {
       Contact currentContact = contactRepository.save(contact);
       Client client = new Client(firstName, lastName);
        return clientRepository.save(client, currentContact.getId());
    }

    @Override
    public Reservation bookRoom(Reservation reservation, String clientEmail, Long roomId) {
        Client client = clientRepository.getByEmail(clientEmail);
        return reservationRepository.save(reservation, client.getId(), roomId);
    }

    @Override
    public Reservation getByClient(String clientEmail) {
        Client client = clientRepository.getByEmail(clientEmail);
        return reservationRepository.getByClient(client.getId());
    }

    @Override
    public int getTotal(String clientEmail) {
        Client client = clientRepository.getByEmail(clientEmail);
        Reservation reservation = reservationRepository.getByClient(client.getId());
        Room room = reservation.getRoom();

        Date checkin =  reservation.getCheckIn();
        Date checkout =  reservation.getCheckOut();

        long delta = checkout.getTime()- checkin.getTime();
        long days = TimeUnit.DAYS.convert(delta, TimeUnit.MILLISECONDS);

        BigDecimal cleaningCosts = reservation.getIsCleaning() ? room.getCleaning().multiply(BigDecimal.valueOf(days)) : BigDecimal.valueOf(0);
        BigDecimal breakfastCosts = reservation.getIsBreakfast() ? room.getBreakfast().multiply(BigDecimal.valueOf(days)) : BigDecimal.valueOf(0);

        BigDecimal total = room.getPricePerNight().multiply(BigDecimal.valueOf(days)).add(cleaningCosts).add(breakfastCosts);


        return total.setScale(0, RoundingMode.UP).intValue();
    }
}
