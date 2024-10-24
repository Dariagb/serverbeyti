package ru.daria.serverbeyti.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.daria.serverbeyti.dao.ManufactureRepository;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.dto.ReservationRequest;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ManufactureRepository manufacturerRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void getProductsByManufacturer() {
        Long manufacturerId = 1L;
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(manufacturerId);

        Product product1 = new Product();
        product1.setManufacturer(manufacturer);

        Product product2 = new Product();
        product2.setManufacturer(null);

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = reservationService.getProductsByManufacturer(manufacturerId);

        assertEquals(1, result.size());
        assertTrue(result.contains(product1));
        assertFalse(result.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void createManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        when(manufacturerRepository.save(manufacturer)).thenReturn(manufacturer);

        Manufacturer createdManufacturer = reservationService.createManufacturer(manufacturer);

        assertEquals(manufacturer, createdManufacturer);
        verify(manufacturerRepository, times(1)).save(manufacturer);
    }

    @Test
    void placeOrder() {
        Manufacturer manufacturer = new Manufacturer();
        ReservationRequest reservationRequest = new ReservationRequest(manufacturer);
        when(manufacturerRepository.save(manufacturer)).thenReturn(manufacturer);

        Manufacturer placedOrder = reservationService.placeOrder(reservationRequest);

        assertEquals(manufacturer, placedOrder);
        verify(manufacturerRepository, times(1)).save(manufacturer);
    }

    @Test
    void findAllOrders() {
        List<Manufacturer> manufacturers = Arrays.asList(new Manufacturer(), new Manufacturer());
        when(manufacturerRepository.findAll()).thenReturn(manufacturers);

        List<Manufacturer> allOrders = reservationService.findAllOrders();

        assertEquals(manufacturers, allOrders);
        verify(manufacturerRepository, times(1)).findAll();
    }

    @Test
    void getJoinInformation() {
        List<OrderResponse> orderResponses = Arrays.asList(new OrderResponse(), new OrderResponse());
        when(manufacturerRepository.getJoinInformation()).thenReturn(orderResponses);

        List<OrderResponse> result = reservationService.getJoinInformation();

        assertEquals(orderResponses, result);
        verify(manufacturerRepository, times(1)).getJoinInformation();
    }

    @Test
    void findProductByCountry() {
        String country = "USA";
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setCountry(country);
        Product product1 = new Product();
        product1.setManufacturer(manufacturer);

        Product product2 = new Product();
        product2.setManufacturer(new Manufacturer());
        product2.getManufacturer().setCountry("Canada"); // Different country

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = reservationService.findProductByCountry(country);

        assertEquals(1, foundProducts.size());
        assertTrue(foundProducts.contains(product1));
        assertFalse(foundProducts.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findProductsByCountrys() {
        String country = "USA";
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productRepository.findProductsByCountry(country)).thenReturn(products);

        List<Product> foundProducts = reservationService.findProductsByCountrys(country);

        assertEquals(products, foundProducts);
        verify(productRepository, times(1)).findProductsByCountry(country);
    }
}