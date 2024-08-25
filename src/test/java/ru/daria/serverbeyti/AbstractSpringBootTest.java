package ru.daria.serverbeyti;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.service.ProductService;


@SpringBootTest(classes = TestBeans.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractSpringBootTest {

    @MockBean
    protected ProductService productService;

    @MockBean
    protected ProductRepository productRepository;
}

