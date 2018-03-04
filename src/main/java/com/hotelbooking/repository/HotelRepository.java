package com.hotelbooking.repository;

import com.hotelbooking.model.Hotel;


public interface HotelRepository {

    Hotel save(Hotel hotel, Long contactId);

    void delete(Long id);

    Hotel get(Long id);

}
