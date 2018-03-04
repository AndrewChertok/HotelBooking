package com.hotelbooking.repository.mock;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryReservationRepositoryImpl implements ReservationRepository {

    private Map<Integer, Map<Long, Reservation>> reservationRepository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Long, Room>> roomRepository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Reservation save(Reservation reservation, Long clientId, Long roomId) {

        Objects.requireNonNull(reservation);
        Map<Long, Reservation> reservations = reservationRepository.computeIfAbsent(clientId.intValue(), ConcurrentHashMap::new);
        if (reservation.isNew()) {
            reservation.setId(counter.incrementAndGet());
            Room room = roomRepository.values().stream().map(m->m.entrySet().stream().
                    filter(map->map.getKey()==roomId).findAny().
                    orElse(null)).findAny().orElse(null).getValue();
            reservation.setRoom(room);
            return reservations.put(reservation.getId(), reservation);
        }
        return reservations.computeIfPresent(reservation.getId(), (id, oldReservation) -> reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.values().forEach(entry->entry.remove(id));
    }

    @Override
    public Reservation get(Long id) {
        return null;
    }

    @Override
    public Reservation getByClient(Long clientId) {
        return null;
    }

    @Override
    public List<Reservation> getAllByHotel(Long hotelId) {
      return  new ArrayList<>(reservationRepository.get(hotelId).values());
    }
}
