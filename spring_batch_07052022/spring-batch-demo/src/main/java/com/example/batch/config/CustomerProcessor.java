package com.example.batch.config;

import com.example.batch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;


public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
//        if(customer.getCountry().equalsIgnoreCase("United States")) {
//            return customer;
//        }else {
//            return null;
//        }

        return customer;
    }
}
