package com.tracking.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    /**
     * Spring creates an automated ID that is used here as the shipment number.
     * The following mapping is used for the database connection via MariaDB:
     * One Customer(shipper/addressee) has many shipments.
     * A Shipment always has only one recipient and one sender.
     * The intersection is therefore the CustomerID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Integer id;

    private String firstName;
    private String lastName;
    private String street;
    private String number;
    private String postcode;
    private String city;
    private String country;

    @OneToMany(mappedBy = "addressee")
    private List<Shipment> listOfAddressee = new ArrayList<>();

    @OneToMany(mappedBy = "shipper")
    private List<Shipment> listOfShipper = new ArrayList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String street, String number, String postcode, String city,
                    String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.number = number;
        this.postcode = postcode;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Shipment> getListOfAddressee() {
        return listOfAddressee;
    }

    public void setListOfAddressee(List<Shipment> listOfAddressee) {
        this.listOfAddressee = listOfAddressee;
    }

    public List<Shipment> getListOfShipper() {
        return listOfShipper;
    }

    public void setListOfShipper(List<Shipment> listOfShipper) {
        this.listOfShipper = listOfShipper;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
