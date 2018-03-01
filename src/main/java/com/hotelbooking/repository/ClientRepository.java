package com.hotelbooking.repository;

import com.hotelbooking.model.Client;

public interface ClientRepository {

    Client save(Client client, Long contactId);

    void delete(Long id);

    Client get(Long id);

    Client getByEmail(String email);
}
