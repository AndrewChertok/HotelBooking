package com.hotelbooking.web.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.data.ContactTestData;
import com.hotelbooking.data.ReservationTestData;
import com.hotelbooking.model.Contact;
import com.hotelbooking.model.Reservation;
import com.hotelbooking.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static com.hotelbooking.data.ClientTestData.*;
import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static com.hotelbooking.data.RoomTestData.ROOM1_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationRestController.class)
public class ReservationRestControllerTest {

    private static final String REST_URL = ReservationRestController.REST_URL + '/';

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void create() throws Exception {
        Contact newContact = ContactTestData.getCreated();

         mvc.perform(post(REST_URL)
                .param("firstName","John")
                .param("lastName", "Smith")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newContact)))
                .andExpect(status().isCreated());
    }

    @Test
    public void bookRoom() throws Exception {
        Reservation newReservation = ReservationTestData.getCreated();

        mvc.perform(post(REST_URL+ROOM1_ID)
                .param("email",CLIENT2.getContact().getEmail())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newReservation)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getByClient() throws Exception {
        mvc.perform(get(REST_URL)
                .param("email", CONTACT1.getEmail()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getTotal() throws Exception {
        mvc.perform(get(REST_URL+"total")
                .param("email", CONTACT1.getEmail()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}