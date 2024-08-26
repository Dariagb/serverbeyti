package ru.daria.serverbeyti;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.SpringApplication;

class ServerbeytiApplicationTests {
    public static void main(String[] args){
        SpringApplication.from( ServerbeytiApplication::main)
                .with(TestBeans.class)
                .run(args);
    }
}
