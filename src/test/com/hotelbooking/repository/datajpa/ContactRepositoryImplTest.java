package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.AbstractRepositoryTest;
import com.hotelbooking.repository.ContactRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static com.hotelbooking.data.ContactTestData.*;


public class ContactRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void save() throws Exception {
         Contact newContact  = getCreated();
         Contact savedContact = contactRepository.save(newContact);
        MATCHER.assertEquals(savedContact, newContact);
    }


    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void delete() throws Exception {
        contactRepository.delete(CONTACT1_ID);
        Contact contact = contactRepository.get(CONTACT1_ID);
    }

    @Test
    public void get() throws Exception {
        Contact contact  = contactRepository.get(CONTACT1_ID);
        MATCHER.assertEquals(CONTACT1, contact);
    }

}