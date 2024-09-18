package ru.daria.serverbeyti.feinKlient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OxideService {

    private final OxideClient oxideClient;

    public OxideService(OxideClient oxideClient) {
        this.oxideClient = oxideClient;
    }

    @GetMapping("/demo/{volume}")
    public Long getOrderWithProduct(@PathVariable Long volume) {
        Long product = oxideClient.calculateOxideForPaint(volume);
        return product;
    }
}



