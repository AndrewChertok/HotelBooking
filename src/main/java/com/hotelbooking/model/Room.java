package com.hotelbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "rooms")
public class Room extends AbstractBaseEntity{

    @Column(name = "number", nullable = false)
    @Range(min = 1, max = 10000)
    @NotNull
    private Integer number;

    @NotNull
    @Column(name = "price_per_night", nullable = false)
    private BigDecimal pricePerNight;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoomType roomType;

    @NotNull
    @Column(name = "breakfast", nullable = false)
    private BigDecimal breakfast;

    @NotNull
    @Column(name = "cleaning", nullable = false)
    private BigDecimal cleaning;

    public Room(){

    }

    public Room(Long id, Integer number, BigDecimal pricePerNight, RoomType roomType, BigDecimal breakfast, BigDecimal cleaning) {
        super(id);
        this.number = number;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.breakfast = breakfast;
        this.cleaning = cleaning;
    }

    public Room(Integer number, BigDecimal pricePerNight, RoomType roomType, BigDecimal breakfast, BigDecimal cleaning) {
        this(null, number, pricePerNight, roomType, breakfast, cleaning);
    }


    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(BigDecimal breakfast) {
        this.breakfast = breakfast;
    }

    public BigDecimal getCleaning() {
        return cleaning;
    }

    public void setCleaning(BigDecimal cleaning) {
        this.cleaning = cleaning;
    }

    @Override
    public String toString() {
        return roomType + " room, " + pricePerNight + " per night";
    }
}