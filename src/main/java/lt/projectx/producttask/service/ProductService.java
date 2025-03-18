package lt.projectx.producttask.service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.entity.Product;
import lt.projectx.producttask.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
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
    public List<Product> findAllByDescription(String description) {
        return productRepository.findAllByDescriptionContainingIgnoreCase(description);
    }
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
    }

    public void deleteProductById(Long id) {
        Optional<Product> maybeProductFromDb = productRepository.findById(id);
        if (maybeProductFromDb.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
    public Product putProductById(Long id, Product product) {
        product.setId(id);
        return productRepository.saveAndFlush(product);
    }
    public Product patchProductById(Long id, Product productFromRequest) {
        Optional<Product> maybeProductFromDb = productRepository.findById(id);
        if (maybeProductFromDb.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }

        Product productFromDb = maybeProductFromDb.get();

        if (StringUtils.isNotBlank(productFromRequest.getName()) &&
                !productFromRequest.getName().equals(productFromDb.getName())) {
            productFromDb.setName(productFromRequest.getName());
        }

        if (productFromRequest.getQuantity() != null  && 
                !productFromRequest.getQuantity().equals(productFromDb.getQuantity())) {
            productFromDb.setQuantity(productFromRequest.getQuantity());
        }

        if (StringUtils.isNotBlank(productFromRequest.getDescription()) &&
                !productFromRequest.getDescription().equals(productFromDb.getDescription())) {
            productFromDb.setDescription(productFromRequest.getDescription());
        }

        if (productFromRequest.getPrice() != null  &&
                !productFromRequest.getPrice().equals(productFromDb.getPrice())) {
            productFromDb.setPrice(productFromRequest.getPrice());
        }

        return productRepository.saveAndFlush(productFromDb);
    }
    
    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public void printProductPage(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        this.getAllProductsPageable(pageable).forEach(System.out::println);
    }

}
