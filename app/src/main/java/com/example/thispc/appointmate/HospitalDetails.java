package com.example.thispc.appointmate;

/**
 * Created by this pc on 15-03-2016.
 */
public class HospitalDetails {

    private int id;
    private String name;
    private String address;
    private long contact;
    private int rating;

    public HospitalDetails(int id, String name, String address, long contact, int rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
