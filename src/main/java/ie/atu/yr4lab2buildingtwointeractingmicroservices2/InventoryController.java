package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public InventoryController(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @GetMapping("/all-products")
    public String getALLProducts() {
        return inventoryServiceClient.getProducts();
    }

    @PostMapping("/new-product")
    public String addInventory(@RequestBody Inventory inventory) {
        return inventoryServiceClient.addDetails(inventory);
    }

    @PutMapping("/new-details/{id}")
    public String updateInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        return inventoryServiceClient.updateDetails(id, inventory);
    }

    @DeleteMapping("/delete-product/{id}")
    public String deleteInventory(@PathVariable long id) {
        return inventoryServiceClient.deleteDetails(id);
    }
}
