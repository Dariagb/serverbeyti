package ru.daria.serverbeyti.feinKlient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "oxide-service", url = "http://localhost:8086")
public interface OxideClient {
    @GetMapping("/demo/{volume}")
    Long calculateOxideForPaint(@RequestParam Long volume);
}


