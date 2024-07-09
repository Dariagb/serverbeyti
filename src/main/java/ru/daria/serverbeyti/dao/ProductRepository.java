package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByShadeNumber(Long shadeNumber);

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.shadeNumber = :shadeNumber")
    Optional<Product> getPaintByShadeNumberAndName(@Param("name") String name, @Param("shadeNumber") Long shadeNumber);

    //Optional<Product>getPaintByShadeNumberAndName(String name, Long shadeNumber);
//
//    Object getById(String name, Long shadeNumber);

//    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.shadeNumber = :shadeNumber")
//    Optional<Product> getPaintByShadeNumberAndName(@Param("name") String name, @Param("shadeNumber") Long shadeNumber);
}

