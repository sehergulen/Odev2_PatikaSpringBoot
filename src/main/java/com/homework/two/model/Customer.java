package com.homework.two.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Customer {
    private static Integer idIndex = 1;
    private Integer id;
    private String name;
    private LocalDateTime registrationDate;
    private Sector sector;

    public Customer(String name, Sector sector) {
        this.id = idIndex;
        this.name = name;
        this.registrationDate = LocalDateTime.now();
        this.sector = sector;
        idIndex++;
    }

    public Customer(String name, Sector sector, LocalDateTime registrationDate) {
        this(name, sector);
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name +
                ", registrationDate=" + registrationDate +
                ", sector=" + sector +
                '}';
    }
}
