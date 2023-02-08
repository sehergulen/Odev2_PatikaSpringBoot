package com.homework.two.impl;

import com.homework.two.model.AvaregeBillOfCustomer;
import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Order;
import com.homework.two.service.BillService;
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
public class BillServiceImpl implements BillService {

    @Autowired
    DataService dataService;

    @Override
    public void addBill(Integer customerId, Order... orders) {
        Bill bill = new Bill(customerId, orders);
        dataService.addBill(bill);
    }

    @Override
    public void addBill(Integer customerId, LocalDateTime billTime, Order... order) {
        Bill bill = new Bill(customerId, billTime, order);
        dataService.addBill(bill);
    }

    @Override
    public List<Bill> listBills() {
        return dataService.getBillList();
    }

    public List<Bill> listBillsWithCustomerMap(Map<Integer, Customer> customerMap) {
        List<Bill> billList = dataService.getBillList()
                .stream()
                .filter(bill -> customerMap.containsKey(bill.getCustomerId()))
                .collect(Collectors.toList());
        return billList;
    }

    public Integer calculateTotalAmountOfBillsOfGivenCustomerMap(Map<Integer, Customer> customerMap) {
        AtomicInteger totalAmountOfBills = new AtomicInteger();
        dataService.getBillList()
                .stream()
                .filter(bill -> customerMap.containsKey(bill.getCustomerId()))
                .forEach(bill -> totalAmountOfBills.addAndGet(bill.getTotalPrice()));
        return totalAmountOfBills.get();
    }

    public List<Bill> listBillsMoreThanTotalPrice(Integer totalPrice) {
        List<Bill> billList = dataService.getBillList()
                .stream()
                .filter(bill -> bill.getTotalPrice() > totalPrice)
                .collect(Collectors.toList());
        return billList;
    }

    public float calculateAverageTotalPriceOfBillsMoreThanTotalPrice(Integer totalPrice) {
        AtomicInteger totalAmountOfBills = new AtomicInteger();
        dataService.getBillList()
                .stream()
                .filter(bill -> bill.getTotalPrice() > totalPrice)
                .forEach(bill -> totalAmountOfBills.addAndGet(bill.getTotalPrice()));
        float sizeOfList = listBillsMoreThanTotalPrice(totalPrice).size();
        return (float) totalAmountOfBills.get() / sizeOfList;
    }

    @Override
    public Map<Integer, Bill> listBillsWithLessThanTotalPrice(Integer totalPrice) {
        Map<Integer, Bill> billMap = dataService.getBillList()
                .stream()
                .filter(bill -> bill.getTotalPrice() < totalPrice)
                .collect(Collectors.toMap(bill -> bill.getCustomerId(), bill -> bill));
        return billMap;
    }

    public Set<Integer> findBillsMoreThanTotalPricesAvarageAtAMonth(Month month, Integer totalPrice) {
        HashMap<Integer, AvaregeBillOfCustomer> customerIdTotalMap = new HashMap<>();
        Set<Integer> customerIds = new HashSet<>();
        for (Bill bill : dataService.getBillList()) {
            if (!bill.getBillTime().getMonth().equals(month)) {
                continue;
            }
            if (customerIdTotalMap.containsKey(bill.getCustomerId())) {
                customerIdTotalMap.get(bill.getCustomerId()).incrementSumOffBills(bill.getTotalPrice());
            } else {
                AvaregeBillOfCustomer avg = new AvaregeBillOfCustomer();
                avg.incrementSumOffBills(bill.getTotalPrice());
                customerIdTotalMap.put(bill.getCustomerId(), avg);
            }
        }
        for (Map.Entry<Integer, AvaregeBillOfCustomer> set : customerIdTotalMap.entrySet()) {
            if (set.getValue().getAverageOfBills() < totalPrice) {
                customerIds.add(set.getKey());
            }
        }
        return customerIds;
    }


}
