package com.homework.two.service;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Order;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BillService {

    void addBill(Integer customerId, Order... order);

    void addBill(Integer customerId, LocalDateTime localDateTime, Order... order);

    List<Bill> listBills();

    List<Bill> listBillsWithCustomerMap(Map<Integer, Customer> customerMap);

    Integer calculateTotalAmountOfBillsOfGivenCustomerMap(Map<Integer, Customer> customerMap);

    List<Bill> listBillsMoreThanTotalPrice(Integer totalPrice);

    float calculateAverageTotalPriceOfBillsMoreThanTotalPrice(Integer totalPrice);

    Map<Integer, Bill> listBillsWithLessThanTotalPrice(Integer totalPrice);

    Set<Integer> findBillsMoreThanTotalPricesAvarageAtAMonth(Month month, Integer totalPrice);
}
