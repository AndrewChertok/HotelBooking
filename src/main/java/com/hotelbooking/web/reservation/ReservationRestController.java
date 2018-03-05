package com.hotelbooking.web.reservation;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = ReservationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationRestController extends ReservationAbstractController{

    static final String REST_URL = "/rest/reservation";


    @Override
    @ResponseStatus(HttpStatus.CREATED)  //create a client
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client create(@RequestBody Contact contact, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
        return super.create(contact, firstName, lastName);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)  //create reservation by existing client
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reservation bookRoom(@RequestBody Reservation reservation, @RequestParam("email") String clientEmail, @PathVariable("id") Long roomId){
        return super.bookRoom(reservation, clientEmail, roomId);
    }

    @Override
    @GetMapping  //client can check reservation using email
    public Reservation getByClient(@RequestParam("email") String clientEmail){
        return super.getByClient(clientEmail);
    }

    @Override
    @GetMapping("/total") //get the total price of booking using client's email
    public int getTotal(@RequestParam("email") String clientEmail){
        return  super.getTotal(clientEmail);
    }

}
