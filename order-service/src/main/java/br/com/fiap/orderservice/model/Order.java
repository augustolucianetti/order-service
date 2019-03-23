package br.com.fiap.orderservice.model;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * email, Nome
 * completo e shipping address, id do pedido, descrição de cada item do pedido,
 * quantidade de itens do pedido, preço unitário de cada item, preço total do
 * pagamento, forma de pagamento, data do pedido e status do pedido.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Order {

    private String email;
    private String completeName;
    private BigInteger id;
    private Integer quantity;
    private List<BigDecimal> priceItems;
    private BigDecimal totalPaymentPrice;
    private String paymentType;
    private String date;
    private String status;
    private String address;
    private Payment payment;
}
