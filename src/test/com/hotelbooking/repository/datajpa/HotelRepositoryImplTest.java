package com.hotelbooking.repository.datajpa;

import com.hotelbooking.data.ContactTestData;
import com.hotelbooking.data.HotelTestData;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Hotel;
import com.hotelbooking.repository.AbstractRepositoryTest;
import com.hotelbooking.repository.ContactRepository;
import com.hotelbooking.repository.HotelRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.hotelbooking.data.HotelTestData.HOTEL1;
import static com.hotelbooking.data.HotelTestData.HOTEL1_ID;
import static com.hotelbooking.data.HotelTestData.MATCHER;



public class HotelRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void save() throws Exception {
        Contact newContact  = ContactTestData.getCreated();
        Contact savedContact = contactRepository.save(newContact);
        Hotel newHotel = HotelTestData.getCreated();
        Hotel savedHotel = hotelRepository.save(newHotel, savedContact.getId());
        MATCHER.assertEquals(newHotel, savedHotel);
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        hotelRepository.delete(HOTEL1_ID);
        Hotel hotel = hotelRepository.get(HOTEL1_ID);
        hotel.getName();

    }

    @Test
    public void get() throws Exception {
        Hotel hotel = hotelRepository.get(HOTEL1_ID);
        MATCHER.assertEquals(HOTEL1, hotel);
    }

}