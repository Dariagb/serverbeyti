package ru.daria.serverbeyti.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    List<ProductDTO> toProductDTOs(List<Product> products);

    ProductDTO toProductDTO(Product product);

    @Mapping(target = "name")
    Product toProduct(ProductDTO productDTO);
}








