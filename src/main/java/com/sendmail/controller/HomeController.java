package com.sendmail.controller;

import com.sendmail.dto.CustomerDTO;
import com.sendmail.dto.InvoiceDTO;
import com.sendmail.dto.ProductDTO;
import com.sendmail.service.EmailService;
import com.sendmail.utils.DateTimeMe;
import com.sendmail.utils.RandomCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import java.text.ParseException;

@Controller("controllerSendMail")
@Slf4j
public class HomeController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String pageIndex() {
        return "index";
    }

    @PostMapping("/send-mail")
    public String sendMail(@RequestParam("email") String email,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone,
                           @RequestParam("address") String address,
                           @RequestParam("pName") String pName,
                           @RequestParam("pCode") String pCode,
                           @RequestParam("pPrice") String pPrice,
                           @RequestParam("pQuantity") String pQuantity){
        try {

            String orderDate = DateTimeMe.getCurrentDate();
            String receivingDate = DateTimeMe.getNextDate(orderDate);

            String invoCode = RandomCode.generateRandomString(6); // Generate Invoice Code (Random with 6 characters)

            String [] arrOfStr = pPrice.split("-", 2); // Slip String by '-'
            if (pQuantity.equals("")){
                pQuantity = "1";
            }
            int quantity = Integer.parseInt(pQuantity);
            double price = Double.parseDouble(arrOfStr[1]);
            double total = price * quantity;

            InvoiceDTO dto = new InvoiceDTO(invoCode, orderDate, receivingDate, total,
                             new CustomerDTO(name, phone, address, email,
                             new ProductDTO(pName, pCode, price, quantity)));

            emailService.sendMail(dto); // Send

        }catch (ArrayIndexOutOfBoundsException | MessagingException | ParseException e){
            log.error("In controller -> HomeController -> /send-mail");
        }
        return "redirect:/";
    }
}
