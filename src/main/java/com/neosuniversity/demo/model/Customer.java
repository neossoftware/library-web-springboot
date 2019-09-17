package com.neosuniversity.demo.model;


public class Customer {

    private Long id;
    private String name;
    private String lastName;
    private String address;
    public Customer(){

    }

    public Customer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Customer(Long id,String name, String lastName,String address) {
        this.id=id;
        this.name = name;
        this.lastName = lastName;
        this.address=address;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
