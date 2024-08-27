package ru.daria.serverbeyti.feinKlient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;


@FeignClient(name = "oxide-service", url = "http://localhost:8086")
public interface OxideClient {
    @GetMapping("/demo/{volume}")
    Product calculateOxideForPaint(@RequestParam Long volume);
}

//@FeignClient(name = "oxide-service", url = "http://localhost:8086/demo")
//public interface OxideService {
//    @GetMapping("/volume")
//    Integer calculateOxideForPaint(@RequestBody Long productDTO);
//}
