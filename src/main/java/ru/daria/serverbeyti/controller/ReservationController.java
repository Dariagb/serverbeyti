package ru.daria.serverbeyti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.dao.ManufactureRepository;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.dto.ReservationRequest;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "products api", description = "управление задачами пользователя.")
public class ReservationController {

    private final ProductRepository productRepository;
    private final ManufactureRepository manufacturerRepository;

    @Operation(summary = "Получить все продукты конкретного производителя по его ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Получен"),
            @ApiResponse(responseCode = "404", description = "Не найден")
    })
    @GetMapping("/manufacturers/{manufacturerId}/products")
    public List<Product> getProductsByManufacturer(@PathVariable Long manufacturerId) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getManufacturer() != null &&
                        product.getManufacturer().getManufacturerId().equals(manufacturerId))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Создать нового производителя.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Товар не создан")
    })
    @PostMapping("/manufacturers")
    public Manufacturer createManufacturer(@RequestBody Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Заказ не создан")
    })
    @PostMapping("/planceOrder")@Operation(summary = "Разместить заказ .")
    public Manufacturer placeOrder(@RequestBody ReservationRequest reservationRequest){
        return manufacturerRepository.save(reservationRequest.getManufacturer());
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Информация получена"),
            @ApiResponse(responseCode = "404", description = "Инфорамция не найдена")
    })
    @GetMapping("/findAllOrders")  @Operation(summary = "Получить информацию о всех заказах")
    public List<Manufacturer> findAllOrders(){
        return manufacturerRepository.findAll();
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Информация получена"),
            @ApiResponse(responseCode = "404", description = "Инфорамция не найдена")
    })
    @GetMapping("/getInfo")  @Operation(summary = "Получить информацию о заказах .")
    public List<OrderResponse>getJoinInformation(){
        return manufacturerRepository.getJoinInformation();
    }
}
