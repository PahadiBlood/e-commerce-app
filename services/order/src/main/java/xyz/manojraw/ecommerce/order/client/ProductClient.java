package xyz.manojraw.ecommerce.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyz.manojraw.ecommerce.exception.ApiException;
import xyz.manojraw.ecommerce.order.product.PurchaseRequest;
import xyz.manojraw.ecommerce.order.product.PurchaseResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${spring.application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;


    public List<PurchaseResponseDto> purchaseProducts(List<PurchaseRequest> requestList) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestList, headers);
        ParameterizedTypeReference<List<PurchaseResponseDto>> responseType =
                new ParameterizedTypeReference<List<PurchaseResponseDto>>() {};

        ResponseEntity<List<PurchaseResponseDto>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw new ApiException(
                    "An error occurred processing purchase", "INTERNAL_SERVER_ERROR",
                    HttpStatus.valueOf(responseEntity.getStatusCode().value())
            );
        }
        return responseEntity.getBody();
    }
}
