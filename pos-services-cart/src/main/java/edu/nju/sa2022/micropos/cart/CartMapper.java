package edu.nju.sa2022.micropos.cart;

import edu.nju.sa2022.micropos.dtos.CartDto;
import edu.nju.sa2022.micropos.models.Cart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    Cart toCart(CartDto cartDto);

    CartDto toCartDto(Cart cart);

}
