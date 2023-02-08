package com.homework.two.service;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;

import java.util.List;

public interface DataService {
    List<Bill> getBillList();

    List<Customer> getCustomerList();

    void addBill(Bill bill);

    void addCustomer(Customer customer);

}
