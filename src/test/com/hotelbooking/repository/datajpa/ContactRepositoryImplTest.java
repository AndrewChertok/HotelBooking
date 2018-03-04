package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Client;
import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.hotelbooking.data.ContactTestData.CONTACT1;
import static com.hotelbooking.data.ContactTestData.CONTACT1_ID;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Sql(scripts = "classpath:db/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class ContactRepositoryImplTest {

    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void save() throws Exception {
         Contact newContact  = new Contact("+44 753 444-33-22", "new@gamil.com", "Hermit st. 55 app.2", "London", "England");
         Contact savedContact = contactRepository.save(newContact);
        assertThat(newContact.getEmail()).isEqualTo(savedContact.getEmail());
    }

    //TODO know why
    @Test(expected = JpaObjectRetrievalFailureException.class)
    public void delete() throws Exception {
        contactRepository.delete(CONTACT1_ID);
        Contact contact = contactRepository.get(CONTACT1_ID);
    }

    @Test
    public void get() throws Exception {
        Contact contact  = contactRepository.get(CONTACT1_ID);
        assertThat(CONTACT1.getEmail()).isEqualTo(contact.getEmail());
    }

}