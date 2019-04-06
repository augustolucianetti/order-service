package br.com.fiap.orderservice.restcontroller;

import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.model.Payment;
import br.com.fiap.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void notFoundOrder() throws Exception {
        mvc.perform(
                get("/findById/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void orderFoun() throws Exception {
        Order order = new Order("augusto@gmailcom", "Augusto Carrara", new BigInteger("1"), 1, new ArrayList<BigDecimal>(),
                new BigDecimal("1.11"), "10/11/2012", "10/10/2010", "finalizado", "Rua Arlindo Favarerto", new Payment());
        when(this.orderRepository.findById(new BigInteger("1"))).thenReturn(order);
        mvc.perform(get("/findById/" + order.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(response -> {
                    String json = response.getResponse().getContentAsString();
                    Order orderFounded = new ObjectMapper().readValue(json, Order.class);
                    Assert.assertNotNull( orderFounded );
                });
    }
}
