package ru.daria.serverbeyti.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.daria.serverbeyti.feinKlient.OxideClient;

@RestController
@RequiredArgsConstructor
public class FeignController {

   private final OxideClient oxideClient;

   @GetMapping("/demo/{volume}")
    public Long getOrderWithProduct(@PathVariable Long volume) {
        Long product = oxideClient.calculateOxideForPaint(volume);
        return product;
    }
}



