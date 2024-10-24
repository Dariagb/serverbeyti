package ru.daria.serverbeyti.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.daria.serverbeyti.dao.ManufactureRepository;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.dto.ReservationRequest;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ProductRepository productRepository;
    private final ManufactureRepository manufacturerRepository;

    public List<Product> getProductsByManufacturer(Long manufacturerId) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getManufacturer() != null &&
                        product.getManufacturer().getManufacturerId().equals(manufacturerId))
                .collect(Collectors.toList());
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    public Manufacturer placeOrder(ReservationRequest reservationRequest) {
        return manufacturerRepository.save(reservationRequest.getManufacturer());
    }

    public List<Manufacturer> findAllOrders() {
        return manufacturerRepository.findAll();
    }

    public List<OrderResponse> getJoinInformation() {
        return manufacturerRepository.getJoinInformation();
    }

    public List<Product> findProductByCountry(String country) {
        return productRepository.findAll().stream()
                .filter(product -> product.getManufacturer().getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }
    public List<Product> findProductsByCountrys(String country) {
        return productRepository.findProductsByCountry(country);
    }
}






