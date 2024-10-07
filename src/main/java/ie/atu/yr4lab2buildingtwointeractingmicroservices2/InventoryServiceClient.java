package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@FeignClient(name = "ProductList", url = "http://localhost:8080/products")
public interface InventoryServiceClient {
    @GetMapping("get")
    public String getAllProducts();

    @GetMapping("get/{id}")
    public Product getProductById(@PathVariable long id);

    @PostMapping("create")
    public String addDetails(@RequestBody Inventory inventory);

    @PutMapping("edit/{id}")
    public String updateDetails(@PathVariable long id, @RequestBody Inventory inventory);

    @DeleteMapping("delete/{id}")
    public String deleteDetails(@PathVariable long id);
}
