package fr.monentreprise.read.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummary {
    private String orderId;
    private String status;
    private BigDecimal total;
}
