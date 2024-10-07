package ru.daria.serverbeyti.feignKlient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "oxide-service", url = "${oxide.service}")
public interface OxideClient {
    @GetMapping("/demo/{volume}")
    Long calculateOxideForPaint(@PathVariable Long volume);
}


