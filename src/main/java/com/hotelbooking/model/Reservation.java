package com.hotelbooking.model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reservations")
public class Reservation extends AbstractBaseEntity{

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "in_date")
    private Date checkInDate;

    @Column(name = "out_date")
    private Date checkOutDate;

    @Column(name = "isBreakfast")
    private Boolean isBreakfast;

    @Column(name = "isCleaning")
    private Boolean isCleaning;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Boolean getIsBreakfast() {
        return isBreakfast;
    }

    public void setIsBreakfast(Boolean isBreakfast) {
        this.isBreakfast = isBreakfast;
    }

    public Boolean getIsCleaning() {
        return isCleaning;
    }

    public void setIsCleaning(Boolean isCleaning) {
        this.isCleaning = isCleaning;
    }

    @Override
    public String toString() {
        return "Reservation{" +"client=" + client + ", room=" + room + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + '}';
    }
}