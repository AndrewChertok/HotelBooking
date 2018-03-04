package com.hotelbooking.data;



import com.hotelbooking.BeanMatcher;
import com.hotelbooking.model.Client;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.data.ContactTestData.*;
import static com.hotelbooking.model.AbstractBaseEntity.START_SEQ;

public class ClientTestData {
    
    public static final Long CLIENT1_ID = intToLong(START_SEQ + 18);

    public static Client CLIENT1 = new Client(CLIENT1_ID, "John", "River");
    public static Client CLIENT2 = new Client(CLIENT1_ID + 1, "Peter", "Smart");
    public static Client CLIENT3 = new Client(CLIENT1_ID + 2, "Lenny", "Bruin");
    public static Client CLIENT4 = new Client(CLIENT1_ID + 3, "Samantha", "Good");
    
    
    static{
        CLIENT1.setContact(CONTACT1);
        CLIENT2.setContact(CONTACT2);
        CLIENT3.setContact(CONTACT3);
        CLIENT4.setContact(CONTACT4);
    }


    public static final List<Client> CLIENTS = Arrays.asList(CLIENT4, CLIENT3, CLIENT2, CLIENT1);

    public static Client getCreated() {
        return new Client(null,"NewFirstName", "NewLastName");
    }


    public static final BeanMatcher<Client> MATCHER = BeanMatcher.of(Client.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getFirstName(), actual.getFirstName())
                            && Objects.equals(expected.getLastName(), actual.getLastName()))
    );




}
    



