package com.homework.two;

import com.homework.two.model.Bill;
import com.homework.two.model.Customer;
import com.homework.two.model.Order;
import com.homework.two.model.Sector;
import com.homework.two.service.BillService;
import com.homework.two.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class TwoApplication implements CommandLineRunner {

    @Autowired
    CustomerService customerService;

    @Autowired
    BillService billService;

    public static void main(String[] args) {
        SpringApplication.run(TwoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime juneMonth = LocalDateTime.of(2022, Month.JUNE, 5, 1, 50);
        // add new customers to customer list
        customerService.addCustomer("Seher", Sector.IT);
        customerService.addCustomer("Kemal", Sector.EDUCATION);
        customerService.addCustomer("Can", Sector.DRINK);
        customerService.addCustomer("Cem", Sector.IT);
        customerService.addCustomer("Canan", Sector.IT, juneMonth);

        // init new objects to bill list

        billService.addBill(1, juneMonth, new Order("computer", 2, 3000));
        billService.addBill(2, juneMonth, new Order("book", 2, 150));
        billService.addBill(3, new Order("coke", 5, 15), new Order("pasta", 3, 100));
        billService.addBill(4, new Order("coke", 5, 15), new Order("pasta", 3, 40));
        billService.addBill(5, new Order("coffee", 3, 20));

        // list customers
        List<Customer> customerList = customerService.listCustomers();
        log.info("list customers:{}", customerList);

        //list all bills
        List<Bill> billList = billService.listBills();
        log.info("list bills:{}", billList);

        // list customers whose name starts with C
        customerList = customerService.listCustomersWithNameLike("C");
        log.info("list customers with C letter:{}", customerList);

        // list bills whose customer registration date is June
        Map<Integer, Customer> customerMap = customerService.listCustomersWithRegistrationMonth(Month.JUNE);
        Integer totalCount = billService.calculateTotalAmountOfBillsOfGivenCustomerMap(customerMap);
        log.info("total amount of bills of customer registration month is june:{}", totalCount);

        // list bills whose total price is bigger than 1500
        billList = billService.listBillsMoreThanTotalPrice(1500);
        log.info("list bills whose total price is bigger than 1500:{}", billList);

        // average of  bills whose total price is bigger than 1500
        float avarage = billService.calculateAverageTotalPriceOfBillsMoreThanTotalPrice(1500);
        log.info("average of  bills whose total price is bigger than 1500:{}", avarage);

        // list of customer whose bills total price less than 500
        Map<Integer, Bill> billMap = billService.listBillsWithLessThanTotalPrice(500);
        Set<String> nameOfCustomers = customerService.listNamesOfCustomerWithBillMap(billMap);
        log.info("list of customer whose bills total price less than 500 : {}", nameOfCustomers);

        // list sectors of customers whose total avaage bills are less than 750 for june
        Set<Integer> customerIds = billService.findBillsMoreThanTotalPricesAvarageAtAMonth(Month.JUNE, 750);
        Set<Sector> sectorSet = customerService.listSectorsOfGivenCustomerIds(customerIds);
        log.info("list sectors of customers whose total avaage bills are less than 750 for june : {}", sectorSet);


    }


}
