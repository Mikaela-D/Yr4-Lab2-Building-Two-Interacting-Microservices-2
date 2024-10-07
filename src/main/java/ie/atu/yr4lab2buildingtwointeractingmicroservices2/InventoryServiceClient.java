package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "ProductList", url = "http://localhost:8080/products")
public interface InventoryServiceClient {
    @GetMapping("/product")
    public String getAllProducts();

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable long id);

/*    @PostMapping("/product")
    public String addDetails(@RequestBody Inventory inventory);

    @PutMapping("/product/{id}")
    public String updateDetails(@PathVariable long id, @RequestBody Inventory inventory);

    @PostMapping("/product/{id}")
    public String deleteDetails(@PathVariable long id);
*/
}
