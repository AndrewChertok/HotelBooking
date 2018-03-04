package com.hotelbooking.data;

import com.hotelbooking.BeanMatcher;
import com.hotelbooking.model.Contact;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hotelbooking.TestUtil.intToLong;
import static com.hotelbooking.model.AbstractBaseEntity.START_SEQ;

public class ContactTestData {

    public static final Long CONTACT1_ID = intToLong(START_SEQ);

    public static final Contact CONTACT1 = new Contact(CONTACT1_ID, "+44 713 335-55-12", "river@gmail.com", "Hermit st. 3 app.77", "London", "England");
    public static final Contact CONTACT2 = new Contact(CONTACT1_ID + 1, "+44 731 356-35-22", "smart@gmail.com", "Friend st. 17 app.25", "London", "England");
    public static final Contact CONTACT3 = new Contact(CONTACT1_ID + 2, "+44 723 638-53-33", "bruin@gmail.com", "Moreland st. 33 app.18", "London", "England");
    public static final Contact CONTACT4 = new Contact(CONTACT1_ID + 3, "+44 742 342-15-44", "good@gmail.com", "Persival st. 221 app.7", "London", "England");
    public static final Contact CONTACT5 = new Contact(CONTACT1_ID + 4, "+44 207 836-43-43", "savoy@gmail.com", "Strand, London WC2R 0EZ", "London", "England");
    public static final Contact CONTACT6 = new Contact(CONTACT1_ID + 5, "+44 742 893-12-78", "hyatt@gmail.com", "30 Portman Square", "London", "England");



    public static final List<Contact> CONTACTS = Arrays.asList(CONTACT6, CONTACT5, CONTACT4, CONTACT3, CONTACT2, CONTACT1);

    public static Contact getCreated() {
        return new Contact(null, "+44 777 888-55-33", "new@gmail.com", "1 Portman Square", "London", "England");
    }


    public static final BeanMatcher<Contact> MATCHER = BeanMatcher.of(Contact.class,
            (expected, actual) -> expected == actual ||
                    (
                             Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getPhone(), actual.getPhone())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getAddress(), actual.getAddress())
                            && Objects.equals(expected.getCity(), actual.getCity())
                            && Objects.equals(expected.getCountry(), actual.getCountry())
                    )
    );

}
