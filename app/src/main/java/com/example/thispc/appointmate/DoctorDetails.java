package com.example.thispc.appointmate;

/**
 * Created by this pc on 16-03-2016.
 */
public class DoctorDetails {
    private int id;
    private String name;
    private long contact;
    private String qualification;

    public DoctorDetails(int id, String name, long contact, String qualification) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.qualification = qualification;
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

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
