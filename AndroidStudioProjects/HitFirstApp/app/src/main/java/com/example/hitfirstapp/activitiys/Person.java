package com.example.hitfirstapp.activitiys;

public class Person {

    public String email;
    public String phone;
    public String address;

    public Person(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

   public Person()
   {

   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
