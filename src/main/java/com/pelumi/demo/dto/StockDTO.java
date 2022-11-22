package com.pelumi.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class StockDTO {
    private String name;

    private double currentPrice;

    public StockDTO(String name) {
        this.name = name;
    }
}
