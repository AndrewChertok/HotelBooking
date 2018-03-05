package com.hotelbooking.web.hotel;


import com.hotelbooking.model.RoomType;
import com.hotelbooking.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.hotelbooking.data.HotelTestData.HOTEL1_ID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(HotelRestController.class)
public class HotelRestControllerTest {

    private static final String REST_URL = HotelRestController.REST_URL + '/';

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HotelService hotelService;

    @Test
    public void getAvailableRooms() throws Exception {
        mvc.perform(get(REST_URL+HOTEL1_ID)
                .param("in", "2018-07-16")
                .param("out","2018-07-19"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void filterByCategory() throws Exception {
        mvc.perform(get(REST_URL+"filter"+"/"+HOTEL1_ID)
                .param("type", "royal"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getReservations() throws Exception {
        mvc.perform(get(REST_URL+"booked"+"/"+HOTEL1_ID))
                .andExpect(status().isOk())
                .andDo(print());
    }

}