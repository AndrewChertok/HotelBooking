package com.hotelbooking.model;




import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reservations")
public class Reservation extends AbstractBaseEntity{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "in_date")
    private Date checkIn;


    @Column(name = "out_date")
    private Date checkOut;

    @Column(name = "breakfast")
    private Boolean isBreakfast;

    @Column(name = "cleaning")
    private Boolean isCleaning;

    public Reservation(){

    }

    public Reservation(Long id, Date checkInDate, Date checkOutDate, Boolean isBreakfast, Boolean isCleaning) {
        super(id);
        this.checkIn = checkInDate;
        this.checkOut = checkOutDate;
        this.isBreakfast = isBreakfast;
        this.isCleaning = isCleaning;
    }

    public Reservation(Date checkIn, Date checkOut, Boolean isBreakfast, Boolean isCleaning) {
        this(null, checkIn, checkOut, isBreakfast, isCleaning);
    }


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

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
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
        return "Reservation{" +"client=" + client + ", room=" + room + ", checkIn=" + checkIn + ", checkOut=" + checkOut + '}';
    }
}