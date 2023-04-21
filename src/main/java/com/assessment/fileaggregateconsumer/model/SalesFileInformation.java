package com.assessment.fileaggregateconsumer.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesFileInformation {
    private String fileName;
    private Date readTime;
    private List<SaleInformation> data;

    public double getTotalSales() {
        return data.stream().mapToDouble(SaleInformation::getTotalPrice).sum();
    }
}
