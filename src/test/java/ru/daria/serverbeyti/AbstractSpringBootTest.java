package ru.daria.serverbeyti;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
