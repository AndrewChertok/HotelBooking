package com.hotelbooking.repository;

import com.hotelbooking.model.Contact;

import java.util.List;

public interface ContactRepository {

    Contact save(Contact contact);

    void delete(Long id);

    Contact get(Long id);
}
