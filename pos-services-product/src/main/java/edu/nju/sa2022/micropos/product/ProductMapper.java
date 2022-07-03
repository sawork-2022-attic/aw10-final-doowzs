package edu.nju.sa2022.micropos.product;

import edu.nju.sa2022.micropos.dtos.ProductDto;
import edu.nju.sa2022.micropos.models.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    List<ProductDto> toProductsDto(List<Product> products);

    List<Product> toProducts(List<ProductDto> products);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product pet);

}
