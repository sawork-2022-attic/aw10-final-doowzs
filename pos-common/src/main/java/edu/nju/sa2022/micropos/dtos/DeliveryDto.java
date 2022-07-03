package edu.nju.sa2022.micropos.dtos;

import lombok.Data;

@Data
public class DeliveryDto {

    private String id;
    private String orderId;
    private Boolean delivered;

}
