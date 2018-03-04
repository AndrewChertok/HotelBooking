package com.hotelbooking.repository.mock;

import com.hotelbooking.model.Hotel;
import com.hotelbooking.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryHotelRepositoryImpl implements HotelRepository {

    private Map<Integer, Map<Long, Hotel>> repository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Hotel save(Hotel hotel, Long contactId) {
        Objects.requireNonNull(hotel);
        Map<Long, Hotel> hotels = repository.computeIfAbsent(contactId.intValue(), ConcurrentHashMap::new);
        if (hotel.isNew()) {
            hotel.setId(counter.incrementAndGet());
            return hotels.put(hotel.getId(), hotel);
        }
        return hotels.computeIfPresent(hotel.getId(), (id, oldHotel) -> hotel);
    }

    @Override
    public void delete(Long id) {
        repository.values().forEach(entry->entry.remove(id));
    }

    @Override
    public Hotel get(Long id) {
        List<Map<Long, Hotel>> hotels = new ArrayList<>(repository.values());
        return hotels.stream().map(m->m.entrySet().stream().
                filter(map->map.getKey()==id).findAny().
                orElse(null)).findAny().orElse(null).getValue();
    }
}
