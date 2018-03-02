package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Client;
import com.hotelbooking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataJpaClientRepositoryImpl implements ClientRepository{

    @Autowired
    CrudClientRepository clientRepository;

    @Autowired
    CrudContactRepository contactRepository;

    @Override
    public Client save(Client client, Long contactId) {
        if (!client.isNew() && get(client.getId()) == null) {
            return null;
        }
        client.setContact(contactRepository.getOne(contactId));
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
         clientRepository.delete(id);
    }

    @Override
    public Client get(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public Client getByEmail(String email) {
        return clientRepository.getByEmail(email);
    }
}
