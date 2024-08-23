package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.dto.ProductDTO;
import ru.daria.serverbeyti.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByShadeNumberAndName(Long shadeNumber, String name);

    @Query("SELECT p FROM Product p WHERE p.name = :name AND p.shadeNumber = :shadeNumber")
    Optional<ProductDTO> getPaintByShadeNumberAndName(@Param("name") String name, @Param("shadeNumber") Long shadeNumber);

    @Modifying
    @Query("UPDATE Product p SET p.volume = :volume WHERE p.name = :name AND p.shadeNumber = :shadeNumber")
    @Transactional
    Product updatePaint(@Param("name") String name, @Param("shadeNumber") Long shadeNumber, @Param("volume") Long volume);
}