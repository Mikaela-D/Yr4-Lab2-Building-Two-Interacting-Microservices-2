package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ProductList", url = "http://localhost:8080/products")
public interface InventoryServiceClient {

    @GetMapping("/get")
        // This matches the endpoint in the ProductController
    String getProducts();

    @GetMapping("/get/{id}")
    String getProductById(@PathVariable("id") long id); // Fetch a product by ID

    @PostMapping("/create")
    String addDetails(@RequestBody Inventory inventory);

    @PutMapping("/edit/{id}")
    String updateDetails(@PathVariable long id, @RequestBody Inventory inventory);

    @DeleteMapping("/delete/{id}")
    String deleteDetails(@PathVariable long id);
}
