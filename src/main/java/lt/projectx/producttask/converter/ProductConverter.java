package lt.projectx.producttask.converter;

import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.dto.CreateProductRequest;
import lt.projectx.producttask.dto.GetProductResponse;
import lt.projectx.producttask.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductConverter {

    public GetProductResponse entityToDto(Product product) {
        GetProductResponse response = new GetProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setDescription(product.getDescription());
        return response;
    }

    public List<GetProductResponse> entityListToDto(List<Product> products) {
        List<GetProductResponse> responses = new ArrayList<>();
        for (Product product : products) {
            responses.add(entityToDto(product));
        }
        return responses;
    }

    public Product toEntity(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        return product;
    }
}
