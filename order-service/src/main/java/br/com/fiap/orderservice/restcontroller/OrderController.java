package br.com.fiap.orderservice.restcontroller;

import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.math.BigInteger;
import java.net.URI;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable BigInteger id) {

        Order order = orderRepository.findById(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.MULTI_STATUS.OK);
        }
        return new ResponseEntity(null, HttpStatus.MULTI_STATUS.OK);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Order order) {
        orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Order order, @PathVariable BigInteger id) {
        Order response = orderRepository.update(order, id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  BigInteger id) {
        orderRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
