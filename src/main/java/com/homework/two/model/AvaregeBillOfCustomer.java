package com.homework.two.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaregeBillOfCustomer {

    private int sumOfBills;
    private int numberOfBills;

    public void incrementSumOffBills(int sumOfBills) {
        this.sumOfBills += sumOfBills;
        numberOfBills += 1;
    }

    public float getAverageOfBills() {
        return sumOfBills / numberOfBills;
    }

    @Override
    public String toString() {
        return "AvaregeBillOfCustomer{" +
                "sumOfBills=" + sumOfBills +
                ", numberOfBills=" + numberOfBills +
                '}';
    }
}
