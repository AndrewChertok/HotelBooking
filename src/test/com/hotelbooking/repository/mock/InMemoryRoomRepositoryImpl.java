package com.hotelbooking.repository.mock;

import com.hotelbooking.model.Reservation;
import com.hotelbooking.model.Room;
import com.hotelbooking.repository.RoomRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryRoomRepositoryImpl implements RoomRepository {


    private Map<Integer, Map<Long, Room>> roomRepository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Long, Reservation>> reservationRepository = new ConcurrentHashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Room save(Room room, Long hotelId) {
        Objects.requireNonNull(room);
        Map<Long, Room> rooms = roomRepository.computeIfAbsent(hotelId.intValue(), ConcurrentHashMap::new);
        if (room.isNew()) {
            room.setId(counter.incrementAndGet());
            return rooms.put(room.getId(), room);
        }
        return rooms.computeIfPresent(room.getId(), (id, oldRoom) -> room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.values().forEach(entry->entry.remove(id));
    }

    @Override
    public Room get(Long id) {
        List<Map<Long, Room>> rooms = new ArrayList<>(roomRepository.values());
        return rooms.stream().map(m->m.entrySet().stream().
                filter(map->map.getKey()==id).findAny().
                orElse(null)).findAny().orElse(null).getValue();
    }

    @Override
    public List<Room> getAllByHotel(Long hotelId) {
        return new ArrayList<>(roomRepository.get(hotelId).values());
    }

    @Override
    public List<Room> getUnoccupiedRooms(Long hotelId, Date checkin, Date checkout) {
        List<Long> roomIds = reservationRepository.get(hotelId).values().stream().
                filter(r->(r.getCheckIn().after(checkin) && r.getCheckIn().before(checkout)) ||
                        (r.getCheckIn().before(checkin) && r.getCheckOut().after(checkout)) ||
                        (r.getCheckOut().after(checkin) && r.getCheckOut().before(checkout))).
                map(entry->entry.getRoom().getId()).collect(Collectors.toList());
        List<Room> rooms = new ArrayList<>(roomRepository.get(hotelId).values());
        return rooms.stream().filter(room->!rooms.contains(room.getId())).collect(Collectors.toList());
    }
}
