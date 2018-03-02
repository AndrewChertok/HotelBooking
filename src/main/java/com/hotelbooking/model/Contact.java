package com.hotelbooking.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "unique_email_idx")})
public class Contact extends AbstractBaseEntity{

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    public Contact(){

    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return "Contact{" + "phone=" + phone + ", email=" + email + ", address=" + address + ", city=" + city + ", country=" + country + '}';
    }
}