package lt.projectx.producttask;

import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.entity.Product;
import lt.projectx.producttask.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductTaskApplication {
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductTaskApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        productService.addTestProducts();
        productService.getAllProducts();
        int kaina = 1;
        String objektas = "muse";
        List<Product> visiProduktai = productService.getAllProducts();
        System.out.println(visiProduktai);

    }


}
