package fr.monentreprise.read.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "order_views")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderView {
    @Id
    private String customerId;
    private List<OrderSummary> orders = new ArrayList<>();
}
