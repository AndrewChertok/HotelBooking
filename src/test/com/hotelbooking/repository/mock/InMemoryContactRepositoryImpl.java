package com.hotelbooking.repository.mock;

import com.hotelbooking.model.Contact;
import com.hotelbooking.repository.ContactRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryContactRepositoryImpl implements ContactRepository {


    private Map<Long, Contact> repository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Contact save(Contact contact) {
        if (contact.isNew()) {
            contact.setId(counter.incrementAndGet());
        }
        repository.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public void delete(Long id) {
        repository.remove(id);
    }

    @Override
    public Contact get(Long id) {
        return repository.get(id);
    }
}
