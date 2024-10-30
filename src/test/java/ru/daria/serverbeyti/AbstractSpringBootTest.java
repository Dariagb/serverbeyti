package ru.daria.serverbeyti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.daria.serverbeyti.dao.ClientsRepository;
import ru.daria.serverbeyti.dao.ManufactureRepository;
import ru.daria.serverbeyti.dao.ProductRepository;
import ru.daria.serverbeyti.dao.WorkersRepository;
import ru.daria.serverbeyti.service.ProductService;
import ru.daria.serverbeyti.service.ReservationService;

@SpringBootTest
@Import( TestBeans.class)
public abstract class AbstractSpringBootTest {
    @MockBean
    protected ProductService productService;

    @MockBean
    protected ProductRepository productRepository;

    @MockBean
    protected ReservationService reservationService;

    @Autowired
    protected ClientsRepository clientsRepository;

    @Autowired
    protected WorkersRepository workersRepository;

    @Autowired
    protected ManufactureRepository manufactureRepository;

}
