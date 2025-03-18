package lt.projectx.producttask;

import lombok.RequiredArgsConstructor;
import lt.projectx.producttask.service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
        productService.printAllProducts();
        System.out.println();
        productService.printProductPage(0);
        System.out.println();
        productService.printProductPage(1);
        System.out.println();
        productService.printProductPage(2);
        System.out.println();
        productService.printProductPage(3);
        System.out.println();
        productService.printProductPage(4);
    }


}
