package ru.daria.serverbeyti.feinKlient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@FeignClient(name = "oxide-service", url = "http://localhost:8086")
//public interface OxideClient {
//    @GetMapping("/demo/{volume}")
//    Long calculateOxideForPaint(@RequestParam Long volume);
//}
@FeignClient(name = "oxide-service", url = "http://localhost:8086")
public interface OxideClient {
    @GetMapping("/demo/{volume}")
    Long calculateOxideForPaint(@PathVariable Long volume);
}


