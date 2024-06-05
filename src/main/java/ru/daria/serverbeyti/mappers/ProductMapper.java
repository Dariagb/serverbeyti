package ru.daria.serverbeyti.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO toProductDTO(Product product);
}
