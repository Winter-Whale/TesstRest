package test.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Integer id;
    private String name;
    private int price;
    private int weight;
    private LocalDateTime created;
}
