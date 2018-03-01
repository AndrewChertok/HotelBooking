package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudContactRepository extends JpaRepository<Contact, Long> {


    @Override
    Contact getOne(Long id);

    @Override
    Contact save(Contact contact);

    @Override
    void delete(Long id);

}
