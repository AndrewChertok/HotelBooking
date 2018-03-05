package com.hotelbooking.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;


@Entity
@Table(name = "clients")
public class Client extends AbstractBaseEntity{

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public Client(){

    }

    public Client(Long id, String firstName, String lastName){
        super(id);
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public Client(String firstName, String lastName){
        this(null, firstName, lastName);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", email: " + contact.getEmail();
    }
}
