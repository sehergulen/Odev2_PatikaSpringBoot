package com.homework.two.service;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Sector;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustomerService {

    void addCustomer(String name, Sector sector);

    void addCustomer(String name, Sector sector, LocalDateTime registrationDate);

    List<Customer> listCustomers();

    List<Customer> listCustomersWithNameLike(String namePrefix);

    Map<Integer, Customer> listCustomersWithRegistrationMonth(Month month);

    Set<String> listNamesOfCustomerWithBillMap(Map<Integer, Bill> billMap);

    Set<Sector> listSectorsOfGivenCustomerIds(Set<Integer> customerIds);
}
