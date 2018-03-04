package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Hotel;
import com.hotelbooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DataJpaHotelRepositoryImpl implements HotelRepository {

    @Autowired
    CrudHotelRepository hotelRepository;

    @Autowired
    CrudContactRepository contactRepository;

    @Override
    public Hotel save(Hotel hotel, Long contactId) {
        if (!hotel.isNew() && get(hotel.getId()) == null) {
            return null;
        }
        hotel.setContact(contactRepository.getOne(contactId));
        return hotelRepository.save(hotel);
    }

    @Override
    public void delete(Long id) {
       hotelRepository.delete(id);
    }

    @Override
    public Hotel get(Long id) {
        return hotelRepository.findOne(id);
    }

}
