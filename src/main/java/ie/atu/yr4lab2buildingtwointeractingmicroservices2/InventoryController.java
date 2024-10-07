package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryServiceClient inventoryServiceClient;
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryServiceClient inventoryServiceClient, InventoryService inventoryService) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/ProductDetails")
    public String getProducts() {
        return inventoryServiceClient.getAllProducts();
    }


    @GetMapping("/allProduct")
    public List<Inventory> getAllProducts() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/details")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    @PutMapping("/details/{id}")
    public Inventory updatedInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    @PostMapping("/details/{id}")
    public Inventory deletedInventory(@PathVariable long id) {
        return inventoryService.deleteInventory(id);
    }

    @GetMapping("/productInventory/{id}")
    public String getProductInventoryById(@PathVariable long id) {
        return inventoryService.getProductInventoryById(id);
    }
}
