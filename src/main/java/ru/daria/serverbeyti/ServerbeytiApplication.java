package ru.daria.serverbeyti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServerbeytiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerbeytiApplication.class, args);
    }
}
