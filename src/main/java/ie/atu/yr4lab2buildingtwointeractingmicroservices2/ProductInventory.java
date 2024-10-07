package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {
    private Long id;
    private String name;
    private Double price;
    private String brand;
    private double quantity;

    @Override
    public String toString() {
        return "ProductInventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
