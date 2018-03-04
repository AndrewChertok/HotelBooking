package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaContactRepositoryImpl implements ContactRepository {

    @Autowired
    CrudContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        if (!contact.isNew() && get(contact.getId()) == null) {
            return null;
        }
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public Contact get(Long id) {
        return contactRepository.getOne(id);
    }

}
