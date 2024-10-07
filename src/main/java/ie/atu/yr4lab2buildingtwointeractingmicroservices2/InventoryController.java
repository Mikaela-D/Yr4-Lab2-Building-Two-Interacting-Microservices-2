package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/")
public class InventoryController {
    private final InventoryServiceClient inventoryServiceClient;
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryServiceClient inventoryServiceClient, InventoryService inventoryService) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.inventoryService = inventoryService;
    }

    @GetMapping("get-products")
    public String getProducts() {
        return inventoryServiceClient.getAllProducts();
    }

    @GetMapping("get-products-by-id/{id}")
    public ResponseEntity<String> getProductInventoryById(@PathVariable long id) {
        try {
            String productInventory = inventoryService.getProductInventoryById(id);
            return new ResponseEntity<>(productInventory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory for product not found\n", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-inventories")
    public List<Inventory> getAllProducts() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("add-inventory")
    public ResponseEntity<String> addInventory(@RequestBody Inventory inventory) {
        inventoryService.addInventory(inventory);
        return new ResponseEntity<>("Inventory successfully added\n", HttpStatus.CREATED);
    }

    @PutMapping("update-inventory/{id}")
    public ResponseEntity<String> updatedInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        try {
            inventoryService.updateInventory(id, inventory);
            return new ResponseEntity<>("Inventory successfully updated\n", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory not found\n", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete-inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable long id) {
        try {
            Inventory deletedInventory = inventoryService.deleteInventory(id);
            return new ResponseEntity<>("Inventory successfully deleted\n", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory not found\n", HttpStatus.NOT_FOUND);
        }
    }
}
