package ru.daria.serverbeyti.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daria.serverbeyti.dao.ProductRepository;
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
    private final ProductRepository productRepository;

    @Operation(summary = "Создать новый продукт, содержащий параметры имени,номера оттенка и объема.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Товар не создан")
    })
    @PostMapping
    public ResponseEntity<Product> createPaint(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.createProductPoint(dto), HttpStatus.OK);
    }

    @Operation(summary = "Обновить наличие объема краски")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @PutMapping("/id")
    public ResponseEntity<?> updatePaint(@RequestParam String name, @RequestParam Long shadeNumber, @RequestParam Long volume) {
        try {
            productRepository.updatePaint(name, shadeNumber, volume);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Обновить наличие краски по всем параметрам")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "\n" + "Успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")})
    @PutMapping
    public ResponseEntity<Product> updateProductPoint(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProductPaint(product), HttpStatus.OK);
    }

    @Operation(summary = "Получить все материалы", description = "Возвращает продукт DTO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAllPaint() {
        return new ResponseEntity<>(productService.readAllProductDTO(), HttpStatus.OK);
    }

    @Operation(summary = "Получить  необходимый объем по имени и номеру оттенка,", description = "Возвращает материал согласно объёму")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно получен"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @GetMapping("/{shadeNumber}/{name}")
    public ResponseEntity<Product> getPaint(@PathVariable Long shadeNumber, @PathVariable String name) {
        Optional<Product> paint = productService.getPaintByShadeNumberAndName(shadeNumber, name);
        return paint.map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Удалить продукт", description = "Удаляем продукт по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно удален"),
            @ApiResponse(responseCode = "404", description = "Товар не найден")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deletePaint(@PathVariable Long id) {
        productService.deleteProductById(id);
        return HttpStatus.OK;
    }
}

