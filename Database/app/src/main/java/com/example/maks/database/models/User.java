package com.example.maks.database.models;


public class User {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String email;
    private String phone;

    public User(Long id,String name,String surname,String address,String email,String phone){
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhone(phone);
    }
    public User(){

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
