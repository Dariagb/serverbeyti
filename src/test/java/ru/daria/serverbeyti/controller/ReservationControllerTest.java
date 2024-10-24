package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.dto.OrderResponse;
import ru.daria.serverbeyti.dto.ReservationRequest;
import ru.daria.serverbeyti.model.Manufacturer;
import ru.daria.serverbeyti.model.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class ReservationControllerTest extends AbstractSpringBootTest {
    @Autowired
    private ReservationController reservationController;

    @Test
    void getProductsByManufacturer() throws Exception {
        Long manufacturerId = 1L;
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(reservationService.getProductsByManufacturer(manufacturerId)).thenReturn(products);

        List<Product> returnedProducts = reservationController.getProductsByManufacturer(manufacturerId);

        assertNotNull(returnedProducts);
        assertEquals(2, returnedProducts.size());
        verify(reservationService, times(1)).getProductsByManufacturer(manufacturerId);
    }

    @Test
    void createManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(1L);

        when(reservationService.createManufacturer(manufacturer)).thenReturn(manufacturer);

        ResponseEntity<Manufacturer> manufacturerResponseEntity = reservationController.createManufacturer(manufacturer);

        assertEquals(HttpStatus.CREATED, manufacturerResponseEntity.getStatusCode());
        assertEquals(manufacturer, manufacturerResponseEntity.getBody());
    }

    @Test
    void placeOrder() {

        ReservationRequest reservationRequest = new ReservationRequest();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(1L);

        when(reservationService.placeOrder(reservationRequest)).thenReturn(manufacturer);

        ResponseEntity<Manufacturer> manufacturerResponseEntity = reservationController.placeOrder(reservationRequest);

        assertEquals(HttpStatus.OK, manufacturerResponseEntity.getStatusCode());
        assertEquals(manufacturer, manufacturerResponseEntity.getBody());
    }

    @Test
    void findAllOrders() {
        List<Manufacturer> manufacturers = Arrays.asList(new Manufacturer(), new Manufacturer());

        when(reservationService.findAllOrders()).thenReturn(manufacturers);

        List<Manufacturer> response = reservationController.findAllOrders();

        assertEquals(manufacturers, response);
        verify(reservationService, times(1)).findAllOrders();
    }

    @Test
    void getJoinInformation() {
        List<OrderResponse> orderResponses = Arrays.asList(new OrderResponse(), new OrderResponse());

        when(reservationService.getJoinInformation()).thenReturn(orderResponses);

        List<OrderResponse> response = reservationController.getJoinInformation();

        assertEquals(orderResponses, response);
        verify(reservationService, times(1)).getJoinInformation();
    }

    @Test
    void getProductsByCountry() {
        String country = "USA";
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(reservationService.findProductByCountry(country)).thenReturn(products);

        List<Product> response = reservationController.getProductsByCountry(country);

        assertEquals(products, response);
        verify(reservationService, times(1)).findProductByCountry(country);
    }

    @Test
    void getProductsByCountrys() {
        String country = "USA";
        List<Product> products = Arrays.asList(new Product(), new Product());

        when(reservationService.findProductsByCountrys(country)).thenReturn(products);

        List<Product> response = reservationController.getProductsByCountrys(country);

        assertEquals(products, response);
        verify(reservationService, times(1)).findProductsByCountrys(country);
    }}