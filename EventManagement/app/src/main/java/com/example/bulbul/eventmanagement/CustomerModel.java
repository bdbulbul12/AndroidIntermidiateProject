package com.example.bulbul.eventmanagement;

/**
 * Created by bulbul on 3/27/2018.
 */

public class CustomerModel {

    String customerId;
    String customerDate;
    String customerMonth;
    String customerYear;
    String name;
    String address;
    String phone;
    String email;

    public CustomerModel(){

    }

    public CustomerModel(String  customerId, String customerDate, String customerMonth,String customerYear, String name, String address, String phone, String email) {
        this.customerId=customerId;
        this.customerDate = customerDate;
        this.customerMonth = customerMonth;
        this.customerYear=customerYear;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getCustomerDate() {
        return customerDate;
    }

    public String getCustomerMonth() {
        return customerMonth;
    }

    public String getCustomerYear() {
        return customerYear;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
