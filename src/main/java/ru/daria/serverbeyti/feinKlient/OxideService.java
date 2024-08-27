package ru.daria.serverbeyti.feinKlient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;


@RestController
public class OxideService {
    @Autowired
    private OxideClient oxideClient;

    @GetMapping("/demo/{volume}")
    public Product getOrderWithProduct(@PathVariable Long volume) {
        Product product = oxideClient.calculateOxideForPaint(volume);
        return product;
    }
}

