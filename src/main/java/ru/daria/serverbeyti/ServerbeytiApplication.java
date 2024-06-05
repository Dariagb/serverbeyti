package ru.daria.serverbeyti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class ServerbeytiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerbeytiApplication.class, args);
    }

}
