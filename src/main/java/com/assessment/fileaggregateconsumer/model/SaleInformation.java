package com.assessment.fileaggregateconsumer.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SaleInformation {
    private Date saleDate;
    private String productName;
    private int quantity;
    private double price;

    public double getTotalPrice() {
        return quantity * price;
    }
}