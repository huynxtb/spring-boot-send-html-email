package com.sendmail.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String code;
    private double price;
    private int quantity;

    public ProductDTO(String name, String code, double price, int quantity) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.quantity = quantity;
    }
}
