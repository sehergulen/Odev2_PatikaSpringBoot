package com.homework.two.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Bill {
    private static Integer idIndex = 1;
    Integer id;
    List<Order> orderList;
    int totalPrice;
    Integer customerId;
    LocalDateTime billTime;

    public Bill(Integer customerId, Order... orders) {
        this.id = idIndex;
        idIndex++;
        this.customerId = customerId;
        this.orderList = Arrays.asList(orders);
        calculateTotalPrice();
        billTime = LocalDateTime.now();
    }

    public Bill(Integer customerId, LocalDateTime billTime, Order... orders) {
        this(customerId, orders);
        this.billTime = billTime;
    }

    private void calculateTotalPrice() {
        this.orderList.stream().forEach(order -> totalPrice += (order.getPrice() * order.getCount()));
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", orderList=" + orderList +
                ", totalPrice=" + totalPrice +
                ", customerId=" + customerId +
                '}';
    }
}
