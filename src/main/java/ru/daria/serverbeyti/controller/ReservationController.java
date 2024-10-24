package ru.daria.serverbeyti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.dto.ReservationRequest;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;
import ru.daria.serverbeyti.service.ReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "products api", description = "управление задачами пользователя.")
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "Получить все продукты конкретного производителя по его ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Получен"),
            @ApiResponse(responseCode = "404", description = "Не найден")
    })
    @GetMapping("/manufacturers/{manufacturerId}/products")
    public List<Product> getProductsByManufacturer(@PathVariable Long manufacturerId) {
        return reservationService.getProductsByManufacturer(manufacturerId);
    }

    @Operation(summary = "Создать нового производителя.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Товар не создан")
    })
    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> createManufacturer(@RequestBody Manufacturer manufacturer) {
        Manufacturer createdManufacturer = reservationService.createManufacturer(manufacturer);
        return new ResponseEntity<>(createdManufacturer, HttpStatus.CREATED);
    }

    @PostMapping("/placeOrder")
    @Operation(summary = "Разместить заказ .")
    public ResponseEntity<Manufacturer>  placeOrder(@RequestBody ReservationRequest reservationRequest){
        Manufacturer manufacturer = reservationService.placeOrder(reservationRequest);
        return new ResponseEntity<>(manufacturer,HttpStatus.OK);
    }

    @GetMapping("/findAllOrders")
    @Operation(summary = "Получить информацию о всех заказах")
    public List<Manufacturer> findAllOrders(){
        return reservationService.findAllOrders();
    }

    @GetMapping("/getInfo")
    @Operation(summary = "Получить информацию о заказах .")
    public List<OrderResponse>getJoinInformation(){
        return reservationService.getJoinInformation();
    }

    @GetMapping("/{country}")
    @Operation(summary = "Получить товары по стране производства")
    public List<Product> getProductsByCountry(@PathVariable String country) {
        return reservationService.findProductByCountry(country);
    }

    @GetMapping("/country/{country}")
    @Operation(summary = "Получить товары по стране производства")
    public List<Product> getProductsByCountrys(@PathVariable String country) {
        return reservationService.findProductsByCountrys(country);
    }
}




