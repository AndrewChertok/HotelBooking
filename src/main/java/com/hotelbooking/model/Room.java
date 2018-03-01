package com.hotelbooking.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "rooms")
public class Room extends AbstractBaseEntity{

    @Column(name = "number")
    private Integer number;

    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "room_type", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "type")
    private RoomType roomType;

    @Column(name = "breakfast")
    private Integer breakfast;

    @Column(name = "cleaning")
    private Integer cleaning;

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

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getCleaning() {
        return cleaning;
    }

    public void setCleaning(Integer cleaning) {
        this.cleaning = cleaning;
    }

    @Override
    public String toString() {
        return roomType + " room, " + pricePerNight + " per night";
    }
}