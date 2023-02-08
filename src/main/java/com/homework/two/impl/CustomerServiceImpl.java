package com.homework.two.impl;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Sector;
import com.homework.two.service.BillService;
import com.homework.two.service.CustomerService;
import com.homework.two.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    DataService dataService;

    @Override
    public void addCustomer(String name, Sector sector) {
        Customer customer = new Customer(name, sector);
        dataService.addCustomer(customer);
    }

    @Override
    public void addCustomer(String name, Sector sector, LocalDateTime registrationDate) {
        Customer customer = new Customer(name, sector, registrationDate);
        dataService.addCustomer(customer);
    }

    @Override
    public List<Customer> listCustomers() {
        return dataService.getCustomerList();
    }

    @Override
    public List<Customer> listCustomersWithNameLike(String namePrefix) {
        List<Customer> customerList = dataService.getCustomerList()
                .stream()
                .filter(customer -> customer.getName().startsWith(namePrefix))
                .collect(Collectors.toList());
        return customerList;
    }

    @Override
    public Map<Integer, Customer> listCustomersWithRegistrationMonth(Month month) {
        Map<Integer, Customer> customerList = dataService.getCustomerList()
                .stream()
                .filter(customer -> customer.getRegistrationDate().getMonth().equals(month))
                .collect(Collectors.toMap(customer -> customer.getId(), customer -> customer));
        return customerList;
    }

    public Set<String> listNamesOfCustomerWithBillMap(Map<Integer, Bill> billMap) {
        Set<String> nameOfCustomer = dataService.getCustomerList()
                .stream()
                .filter(customer -> billMap.containsKey(customer.getId()))
                .map(Customer::getName)
                .collect(Collectors.toSet());
        return nameOfCustomer;
    }

    @Override
    public Set<Sector> listSectorsOfGivenCustomerIds(Set<Integer> customerIds) {
        Set<Sector> sectorSet = dataService.getCustomerList()
                .stream()
                .filter(customer -> customerIds.contains(customer.getId()))
                .map(Customer::getSector)
                .collect(Collectors.toSet());
        return sectorSet;
    }


}
