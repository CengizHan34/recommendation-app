package com.example.eltprocessapp;

import com.example.eltprocessapp.service.CategoryProductSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author created by cengizhan on 6.07.2021
 */
@SpringBootApplication
public class EtlProcessApp implements CommandLineRunner {
    @Autowired
    private CategoryProductSaleService categoryProductSaleService;

    public static void main(String[] args) {
        SpringApplication.run(EtlProcessApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        categoryProductSaleService.saveCategoryProductSalesListToPostgresDatabase();
    }
}
