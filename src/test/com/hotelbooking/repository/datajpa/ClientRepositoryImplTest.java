package com.hotelbooking.repository.datajpa;


import com.hotelbooking.data.ClientTestData;
import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.AbstractRepositoryTest;
import com.hotelbooking.repository.ClientRepository;
import com.hotelbooking.repository.ContactRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.hotelbooking.data.ClientTestData.CLIENT1;
import static com.hotelbooking.data.ClientTestData.CLIENT1_ID;
import static com.hotelbooking.data.ClientTestData.MATCHER;
import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static com.hotelbooking.data.ContactTestData.getCreated;



public class ClientRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;


    @Test
    public void save() throws Exception {
       Client newClient = ClientTestData.getCreated();
      Contact  newContact = getCreated();
       Contact savedContact = contactRepository.save(newContact);
       Client savedClient =  clientRepository.save(newClient, savedContact.getId());
        MATCHER.assertEquals(savedClient, newClient);
    }

    @Test
    public void get() throws Exception {
        Client client = clientRepository.get(CLIENT1_ID);
        MATCHER.assertEquals(CLIENT1, client);
    }

    @Test
    public void getByEmail() throws Exception {
        Client client = clientRepository.getByEmail(CONTACT1.getEmail());
        MATCHER.assertEquals(CLIENT1, client);
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        clientRepository.delete(CLIENT1_ID);
        Client client = clientRepository.get(CLIENT1_ID);
        client.getContact();

    }

}