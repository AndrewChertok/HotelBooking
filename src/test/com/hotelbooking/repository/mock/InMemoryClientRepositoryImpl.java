package com.hotelbooking.repository.mock;

import com.hotelbooking.model.Client;
import com.hotelbooking.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryClientRepositoryImpl implements ClientRepository {

    private Map<Integer, Map<Long, Client>> repository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Client save(Client client, Long contactId) {

        Objects.requireNonNull(client);
        Map<Long, Client> clients = repository.computeIfAbsent(contactId.intValue(), ConcurrentHashMap::new);
        if (client.isNew()) {
            client.setId(counter.incrementAndGet());
            return clients.put(client.getId(), client);
        }
        return clients.computeIfPresent(client.getId(), (id, oldClient) -> client);
    }

    @Override
    public void delete(Long id) {
       repository.values().forEach(entry->entry.remove(id));
    }

    @Override
    public Client get(Long id) {
        List<Map<Long, Client>> clients = new ArrayList<>(repository.values());
        return clients.stream().map(m->m.entrySet().stream().
                filter(map->map.getKey()==id).findAny().
                orElse(null)).findAny().orElse(null).getValue();
    }

    @Override
    public Client getByEmail(String email) {
        List<Map<Long, Client>> clients = new ArrayList<>(repository.values());
        return clients.stream().map(m->m.entrySet().stream().
                filter(map->map.getValue().getContact().getEmail().equals(email)).findAny().
                orElse(null)).findAny().orElse(null).getValue();
    }
}
