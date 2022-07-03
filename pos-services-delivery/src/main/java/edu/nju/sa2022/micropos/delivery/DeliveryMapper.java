package edu.nju.sa2022.micropos.delivery;

import edu.nju.sa2022.micropos.dtos.DeliveryDto;
import edu.nju.sa2022.micropos.models.Delivery;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeliveryMapper {

    List<DeliveryDto> toDeliveryDtos(List<Delivery> deliveries);

    DeliveryDto toDeliveryDto(Delivery delivery);

}
