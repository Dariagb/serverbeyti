package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.mappers.ProductMapper;
import ru.daria.serverbeyti.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void saveProduct() {
        Product product = new Product();
        productRepository.save(product);
    }

    public void getProduct(int number, int volume) throws InsufficientVolumeException, ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findByShadeNumber(number);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int currentVolume = product.getVolume();
            if (volume < currentVolume) {
                product.setVolume(currentVolume - volume);
                productRepository.save(product);
            } else {
                throw new InsufficientVolumeException("Запрошенный вами объем больше, чем есть в наличии");
            }
        } else {
            throw new ProductNotFoundException("Продукт с номером: " + number + " отсутствует");
        }
    }

    public List<ProductDTO> readAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toProductDTOs(products);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product create(ProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .volume(dto.getVolume())
                .build();
        return productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
