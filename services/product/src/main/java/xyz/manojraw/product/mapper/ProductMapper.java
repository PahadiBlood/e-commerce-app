package xyz.manojraw.product.mapper;

import org.mapstruct.Mapper;
import xyz.manojraw.product.dto.product.ProductRequestDto;
import xyz.manojraw.product.dto.product.ProductResponseDto;
import xyz.manojraw.product.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

    Product toEntity(ProductRequestDto productRequestDto);

    List<ProductResponseDto> toListDto(List<Product> products);

}
