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
import ru.daria.serverbeyti.service.InsufficientVolumeException;
import ru.daria.serverbeyti.service.ProductNotFoundException;
import ru.daria.serverbeyti.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
@Tag(name = "Products API", description = "Manage user tasks.")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get a product by shadeNumber, volume", description = "Returns a product as per the volume")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found -The product was not found")
    })
    @GetMapping("/id")
    public ResponseEntity<?> getProductBy(@RequestParam Integer shadeNumber, @RequestParam Integer volume) {
        try {
            productService.getProduct(shadeNumber, volume);
        } catch (Exception | InsufficientVolumeException | ProductNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "A new product is created containing name and volume parameters")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found -The product was not found")
    })
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    @Operation(summary = "Get all product", description = "Returns a productDTO")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found -The product was not found")
    })
    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() {
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }


    @Operation(summary = "Delete products", description = "Delete products by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found -The product was not found")
    })
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        productService.delete(id);
        return HttpStatus.OK;
    }
}

