package lt.projectx.producttask.service;

import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.entity.Product;
import lt.projectx.producttask.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public void addTestProducts() {
        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName("Test product " + i);
            product.setPrice(100);
            product.setQuantity(100);
            product.setDescription("Test product description " + i);
            addProduct(product);
        }
    }

    public void printAllProducts() {
        this.getAllProducts().forEach(System.out::println);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public void printProductPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        this.getAllProductsPageable(pageable).forEach(System.out::println);
    }

}
