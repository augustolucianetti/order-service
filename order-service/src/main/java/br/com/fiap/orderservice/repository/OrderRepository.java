package br.com.fiap.orderservice.repository;

import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.model.Payment;
import br.com.fiap.orderservice.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.rmi.CORBA.Util;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {


    List<Order> orders = new ArrayList<>();

    public BigInteger save(Order order) {
        orders.add(order);
        return order.getId();
    }

    public Order findById(BigInteger id) {

        for(Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }

        return null;
    }

    public Order update(Order order, BigInteger id) {
        Order response= null;
        for(Order item : orders) {
            if (item.getId().equals(id)) {
                order.getId().equals(item.getId());
                item.setEmail(order.getEmail());
                item.setCompleteName(order.getCompleteName());
                item.setAddress(order.getAddress());
                item.setDate(order.getDate());
                item.setQuantity(order.getQuantity());
                item.setStatus(item.getStatus());
                item.setTotalPaymentPrice(order.getTotalPaymentPrice());
                item.setPriceItems(order.getPriceItems());
                item.setPayment(order.getPayment());
                response = item;
            }
        }
        return response;
    }

    public void delete(BigInteger id) {
        Order removerOrder = null;
        for(int i =0; i <orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                removerOrder = orders.get(i);
                break;
            }
        }
        if (removerOrder != null)
        orders.remove(removerOrder);
    }
}
