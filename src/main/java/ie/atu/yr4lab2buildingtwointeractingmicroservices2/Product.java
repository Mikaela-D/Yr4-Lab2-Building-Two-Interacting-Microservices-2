package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotNull(message = "Cannot be empty")
    private long id;

    @NotBlank(message = "Cannot be blank")
    @Size(min = 2, max = 25, message = "The name must be between 2 to 25 characters")
    private String name;

    @PositiveOrZero(message = "Must be a positive number or zero")
    @Max(value = 3000, message = "The maximum is 3000")
    private double price;
}
