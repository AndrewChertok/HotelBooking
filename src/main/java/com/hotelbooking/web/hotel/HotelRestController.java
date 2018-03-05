package com.hotelbooking.web.hotel;


import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.RoomType;
import com.hotelbooking.util.RoomTypeEnumConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = HotelRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelRestController extends HotelAbstractController{

    static final String REST_URL = "/rest/hotel";


    @Override
    @GetMapping("/{id}")    //get all available rooms in specific hotel by specific period
    public List<Room> getAvailableRooms(@PathVariable("id") Long hotelId, @RequestParam("in") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkin, @RequestParam("out") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkout){
        return super.getAvailableRooms(hotelId, checkin, checkout);
    }

    @Override
    @GetMapping("/filter/{id}") //get all rooms in specific hotel by room type
    public List<Room> filterByCategory(@PathVariable("id") Long hotelId, @RequestParam("type") RoomType roomType){
        return super.filterByCategory(hotelId, roomType);
    }

    @Override
    @GetMapping("booked/{id}")  //all booked rooms in specific hotel
    public List<Reservation> getReservations(@PathVariable("id") Long hotelId){
        return super.getReservations(hotelId);
    }

}
