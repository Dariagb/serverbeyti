package ru.daria.serverbeyti;
import org.springframework.boot.SpringApplication;

class ServerbeytiApplicationTests {
    public static void main(String[] args){
        SpringApplication.from( ServerbeytiApplication::main)
                .with(TestBeans.class)
                .run(args);
    }
}
