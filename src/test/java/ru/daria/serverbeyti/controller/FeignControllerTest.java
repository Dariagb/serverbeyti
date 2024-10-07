package ru.daria.serverbeyti.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.daria.serverbeyti.AbstractSpringBootTest;
import ru.daria.serverbeyti.feignKlient.OxideClient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FeignControllerTest extends AbstractSpringBootTest {

    @MockBean
    private OxideClient oxideClient;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getOrderWithProduct_test() throws Exception {
        Long expectedOxide = 123L;
        Long volume = 10L;

        when(oxideClient.calculateOxideForPaint(volume)).thenReturn(expectedOxide);

        mockMvc.perform(MockMvcRequestBuilders.get("/demo/{volume}", volume)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedOxide.toString()));
    }
}