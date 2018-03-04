package com.hotelbooking.repository.datajpa;

import com.hotelbooking.SpringBootApplication;
import com.hotelbooking.data.ContactTestData;
import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.ClientRepository;
import com.hotelbooking.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static com.hotelbooking.data.ClientTestData.*;
import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:db/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ClientRepositoryImplTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactRepository contactRepository;


    @Test
    public void save() throws Exception {
       Client newClient = new Client("Alex", "New");
      Contact  newContact = new Contact("+44 753 444-33-22", "new@gamil.com", "Hermit st. 55 app.2", "London", "England");
       Contact savedContact = contactRepository.save(newContact);
       Client savedClient =  clientRepository.save(newClient, savedContact.getId());
        Client client = clientRepository.get(savedClient.getId());
        assertThat(client.getLastName()).isEqualTo(newClient.getLastName());
    }

    @Test
    public void get() throws Exception {
        Client client = clientRepository.get(CLIENT1_ID);
        assertThat(CLIENT1.getLastName()).isEqualTo(client.getLastName());
    }

    @Test
    public void getByEmail() throws Exception {
        Client client = clientRepository.getByEmail(CONTACT1.getEmail());
        assertThat(CLIENT1.getLastName()).isEqualTo(client.getLastName());
    }

    @Test(expected = NullPointerException.class)
    public void delete() throws Exception {
        clientRepository.delete(CLIENT1_ID);
        Client client = clientRepository.get(CLIENT1_ID);
        client.getContact();

    }

}