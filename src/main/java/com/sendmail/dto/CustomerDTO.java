package com.sendmail.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String phone;
    private String address;
    private String email;
    private ProductDTO productDTO;

    public CustomerDTO(String name, String phone, String address, String email, ProductDTO productDTO) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.productDTO = productDTO;
    }
}
