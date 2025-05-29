package myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanyServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CompanyServiceApp.class, args);
    }
}

