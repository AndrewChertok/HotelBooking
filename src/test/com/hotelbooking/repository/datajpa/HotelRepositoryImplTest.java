package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Hotel;
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
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:db/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class HotelRepositoryImplTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void save() throws Exception {
        Contact newContact  = new Contact("+44 753 444-33-22", "new@gamil.com", "Hermit st. 55 app.2", "London", "England");
        Contact savedContact = contactRepository.save(newContact);
        Hotel newHotel = new Hotel("The New", savedContact);
        Hotel savedHotel = hotelRepository.save(newHotel, savedContact.getId());
        assertThat(newHotel.getName()).isEqualTo(savedHotel.getName());
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
        assertThat(HOTEL1.getName()).isEqualTo(hotel.getName());
    }

}