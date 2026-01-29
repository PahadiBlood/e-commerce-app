package xyz.manojraw.ecommerce.product.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.ecommerce.product.dto.product.ProductRequestDto;
import xyz.manojraw.ecommerce.product.dto.product.ProductResponseDto;
import xyz.manojraw.ecommerce.product.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

    Product toEntity(ProductRequestDto productRequestDto);

    List<ProductResponseDto> toListDto(List<Product> products);

}
