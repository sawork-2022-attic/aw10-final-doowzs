package edu.nju.sa2022.micropos.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    private String userId;

    @Builder.Default
    private Map<String, Integer> items = new HashMap<>();

    public void addItem(String productId, Integer quantity) {
        if (quantity == 0) {
            removeItem(productId);
        } else {
            items.put(productId, quantity);
        }
    }

    public void removeItem(String productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

}
