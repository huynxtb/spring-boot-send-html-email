package com.sendmail.dto;

import lombok.Data;


@Data
public class InvoiceDTO {
    private String code;
    private String orderDate;
    private String receivingDate;
    private double total;
    private CustomerDTO customerDTO;

    public InvoiceDTO(String code, String orderDate, String receivingDate, double total, CustomerDTO customerDTO) {
        this.code = code;
        this.orderDate = orderDate;
        this.receivingDate = receivingDate;
        this.total = total;
        this.customerDTO = customerDTO;
    }
}
