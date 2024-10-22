package ru.daria.serverbeyti.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByShadeNumberAndName(Long shadeNumber, String name);

    @Modifying
    @Query("UPDATE Product p SET p.volume = :volume WHERE p.name = :name AND p.shadeNumber = :shadeNumber")
    @Transactional
    void updatePaint(@Param("name") String name, @Param("shadeNumber") Long shadeNumber, @Param("volume") Long volume);

    @Query("SELECT p FROM Product p LEFT JOIN p.manufacturer m WHERE m.country = :country")
    List<Product> findProductsByCountry(@Param("country") String country);
}

