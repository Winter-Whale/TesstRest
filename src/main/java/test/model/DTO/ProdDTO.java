package test.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdDTO {

    private Integer id;
    private String name;
    private int price;
    private int weight;
}
