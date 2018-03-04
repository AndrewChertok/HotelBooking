package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudContactRepository extends JpaRepository<Contact, Long> {

    @Override
    @Transactional
    Contact save(Contact contact);

    @Override
    Contact getOne(Long id);

    @Override
    @Transactional
    void delete(Long id);

}
