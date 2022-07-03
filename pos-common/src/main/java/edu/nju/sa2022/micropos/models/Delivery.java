package edu.nju.sa2022.micropos.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    private String id;

    private String orderId;
    private Boolean delivered;

}
