package lt.projectx.producttask.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateProductRequest {
    private String name;
    private Integer price;
    private Integer quantity;
    private String description;
}
