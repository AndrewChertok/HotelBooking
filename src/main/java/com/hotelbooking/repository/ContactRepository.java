package com.hotelbooking.repository;

import com.hotelbooking.model.Contact;

public interface ContactRepository {

    Contact save(Contact contact);

    void delete(Long id);

    Contact get(Long id);
}
