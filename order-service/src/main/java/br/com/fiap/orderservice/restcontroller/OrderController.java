package br.com.fiap.orderservice.restcontroller;

import br.com.fiap.orderservice.exceptions.BadRequestException;
import br.com.fiap.orderservice.exceptions.NotFoundException;
import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.model.ResponseException;
import br.com.fiap.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.math.BigInteger;
import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable BigInteger id) {

        int compare = id.compareTo(new BigInteger("10"));

        if (compare == 1) {
            return new BadRequestException().handleAllExceptions(new ResponseException(LocalDateTime.now(), "Parametro id náo encontrado", HttpStatus.BAD_REQUEST));
        }

        Order order = orderRepository.findById(id);
        if (order != null) {
            return new ResponseEntity(order, HttpStatus.MULTI_STATUS.OK);
        } else {
            throw new NotFoundException("Order with id ".concat(id.toString()).concat(" not found"));
        }
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Order order) {

        if (order.getId() == null) {
            throw new NotFoundException("id of Order not passed");
        }
        orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Order order, @PathVariable BigInteger id) {

        Order orderDatabase = orderRepository.findById(id);
        if (orderDatabase == null) {
            throw new NotFoundException("Order with id ".concat(id.toString()).concat(" not found"));
        }

        Order response = orderRepository.update(order, id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  BigInteger id) {

        Order orderDatabase = orderRepository.findById(id);
        if (orderDatabase == null) {
            throw new NotFoundException("Order with id ".concat(id.toString()).concat(" not found"));
        }
        orderRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
