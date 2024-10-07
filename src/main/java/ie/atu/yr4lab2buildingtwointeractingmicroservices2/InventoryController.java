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

    @GetMapping("get-products") // Gets all products
    public String getProducts() {
        return inventoryServiceClient.getAllProducts();
    }

    @GetMapping("get-products-by-id/{id}") // Gets product and corresponding inventory
    public ResponseEntity<String> getProductInventoryById(@PathVariable long id) {
        try {
            String productInventory = inventoryService.getProductInventoryById(id);
            return new ResponseEntity<>(productInventory, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory for product not found\n", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-inventories") // Gets all inventories
    public List<Inventory> getAllProducts() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("create-inventory") // Creates an inventory
    public ResponseEntity<String> createInventory(@RequestBody Inventory inventory) {
        inventoryService.createInventory(inventory);
        return new ResponseEntity<>("Inventory successfully created\n", HttpStatus.CREATED);
    }

    @PutMapping("edit-inventory/{id}") // Edits an inventory
    public ResponseEntity<String> editInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        try {
            inventoryService.editInventory(id, inventory);
            return new ResponseEntity<>("Inventory successfully edited\n", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory not found\n", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete-inventory/{id}") // Deletes an inventory
    public ResponseEntity<String> deleteInventory(@PathVariable long id) {
        try {
            Inventory deletedInventory = inventoryService.deleteInventory(id);
            return new ResponseEntity<>("Inventory successfully deleted\n", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Inventory not found\n", HttpStatus.NOT_FOUND);
        }
    }
}
