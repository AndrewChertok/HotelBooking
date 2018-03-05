package com.hotelbooking.repository.datajpa;

import com.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudHotelRepository extends JpaRepository<Hotel, Long> {

    @Override
    Hotel findOne(Long id);

    @Override
    @Transactional
    Hotel save(Hotel hotel);

    @Override
    @Transactional
    void delete(Long id);

}
