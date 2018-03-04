package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudClientRepository extends JpaRepository<Client, Long>{


    @Override
    @Transactional
    Client save(Client client);

    @Override
    @Transactional
    void delete(Long id);

    @Override
    Client findOne(Long id);

    @Query("SELECT c FROM Client c WHERE c.contact.email=:email")
    Client getByEmail(@Param("email") String email);

}
