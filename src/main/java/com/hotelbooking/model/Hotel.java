package com.hotelbooking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "hotels")

public class Hotel extends AbstractBaseEntity{

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
    private List<Room> rooms;

    public Hotel(){

    }

    public Hotel(Long id, String name, Contact contact) {
        super(id);
        this.name = name;
        this.contact = contact;
    }

    public Hotel(String name, Contact contact) {
        this(null, name, contact);
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public List<Room> getRooms() {
        return this.rooms;
    }


    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    @Override
    public String toString() {
        return name + ", " + contact.getAddress() + ", " + contact.getCity() + ", " + contact.getCountry();
    }
}