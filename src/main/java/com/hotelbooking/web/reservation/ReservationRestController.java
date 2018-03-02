package com.hotelbooking.web.reservation;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = ReservationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationRestController extends ReservationAbstractController{

    static final String REST_URL = "/rest/reservation";

    @Override
    @PostMapping(value = "/client",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client create(@RequestBody Contact contact, @RequestBody Client client){
        return super.create(contact, client);
    }

    @Override
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reservation bookRoom(@RequestBody Reservation reservation, @RequestParam("email") String clientEmail, @PathVariable("id") Long roomId){
        return super.bookRoom(reservation, clientEmail, roomId);
    }

    @Override
    @GetMapping("/{id}")
    public Reservation getByClient(@PathVariable("id") String clientEmail){
        return super.getByClient(clientEmail);
    }

    @Override
    @GetMapping("/total")
    public int getTotal(@RequestParam("email") String clientEmail){
        return  super.getTotal(clientEmail);
    }

}
