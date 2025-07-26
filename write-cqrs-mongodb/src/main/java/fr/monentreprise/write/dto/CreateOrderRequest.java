package fr.monentreprise.write.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {
    private String customerId;
    private BigDecimal total;
}
