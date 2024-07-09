package ru.daria.serverbeyti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import ru.daria.serverbeyti.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
@Tag(name = "products api", description = "управление задачами пользователя.")
public class ProductController {

    private final ProductService productService;

//    @Operation(summary = "Получаем  необходимый объем по имени, номеру оттенка,", description = "Возвращает материал согласно объёму")
//    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
//            @ApiResponse(responseCode = "404", description = "Товар не найден")
//    })
//    @GetMapping("/id")
//    public ResponseEntity<?> getPaint(@RequestParam String name, @RequestParam Long shadeNumber, @RequestParam Long volume) {
//        try {
//            productService.getPaintByShadeNumberAndName(name, shadeNumber, volume);
//        } catch (Exception | InsufficientVolumeException | ProductNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok().build();
//    }

    @Operation(summary = "Создается новый продукт, содержащий параметры имени и объема.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @PostMapping
    public ResponseEntity<Product> createPaint(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.createProductPoint(dto), HttpStatus.OK);
    }

    @Operation(summary = "Получить все материалы", description = "Возвращает продукт DTO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description ="Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAllPaint() {
        return new ResponseEntity<>(productService.readAllProductDTO(), HttpStatus.OK);
    }

//    @GetMapping("/{name}/{shadeNumber}")
//    public ResponseEntity<Product> getProductByShadeNumberAndName(@PathVariable String name, @PathVariable Long shadeNumber) {
//        Optional<Product> product = productService.getPaintByShadeNumberAndName(name, shadeNumber);
//        return product.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
@GetMapping("/{name}/{shadeNumber}") // получить все
public ResponseEntity<Product> getPaint(@PathVariable String name, @PathVariable Long shadeNumber) {
    Optional<Product> ts = productService.getPaintByShadeNumberAndName(name,shadeNumber);

    if (ts.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(ts.get());
    }

    return ResponseEntity.notFound().build();
}

    @Operation(summary = "Обновить наличие краски")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description ="Успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")})
    @PutMapping
    public ResponseEntity<Product> updatePaint(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProductPaint(product), HttpStatus.OK);
    }

    @Operation(summary = "Удалить продукт", description = "Удаляем продукт по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно завершон"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deletePaint(@PathVariable Long id) {
        productService.deleteProductById(id);
        return HttpStatus.OK;
    }
}

