package com.homework.two.impl;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Order;
import com.homework.two.model.Sector;
import com.homework.two.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    List<Customer> customerList;
    List<Bill> billList;

    @PostConstruct
    void initCustomersAndBills() {
        // init lists
        customerList = new ArrayList<>();
        billList = new ArrayList<>();
    }

    public List<Bill> getBillList() {
        return billList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void addBill(Bill bill) {
        billList.add(bill);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

}
