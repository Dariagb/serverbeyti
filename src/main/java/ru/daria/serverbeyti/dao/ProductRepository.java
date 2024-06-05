package ru.daria.serverbeyti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daria.serverbeyti.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
void findBy(int shadeNumber);

}
