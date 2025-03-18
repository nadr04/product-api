package lt.projectx.producttask.controller;

import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.converter.ProductConverter;
import lt.projectx.producttask.dto.CreateProductRequest;
import lt.projectx.producttask.dto.GetProductResponse;
import lt.projectx.producttask.entity.Product;
import lt.projectx.producttask.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductConverter converter;


    @GetMapping
    public List<GetProductResponse> getProductList(@RequestParam(required = false) String description) {
        if (description != null) {
            return converter.entityListToDto(productService.findAllByDescription(description));
        }

        return converter.entityListToDto(productService.getAllProducts());
    }

    @PostMapping
    public GetProductResponse createProduct(@RequestBody CreateProductRequest request) {
        Product product = converter.toEntity(request);
        GetProductResponse response = converter.entityToDto(productService.addProduct(product));
        return response;
    }

    @GetMapping("/{id}")
    public GetProductResponse getProductById(@PathVariable Long id) {
        return converter.entityToDto(productService.findProductById(id));
    }

    @PutMapping("/{id}")
    public GetProductResponse putProductById(@PathVariable Long id, @RequestBody CreateProductRequest request) {
        Product product = converter.toEntity(request);
        return converter.entityToDto(productService.putProductById(id, product));
    }

    @PatchMapping("/{id}")
    public GetProductResponse patchProductById(@PathVariable Long id, @RequestBody CreateProductRequest request) {
        Product product = converter.toEntity(request);
        return converter.entityToDto(productService.patchProductById(id, product));
    }


    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}