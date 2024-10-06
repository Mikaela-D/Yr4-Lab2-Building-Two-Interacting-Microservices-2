package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private final List<Inventory> inventoryList = new ArrayList<>();

    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public InventoryService(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public String addInventory(Inventory inventory) {
        // Fetch product details from the Product microservice using Feign
        String productResponse = inventoryServiceClient.getProductById(inventory.getId());

        if (productResponse != null) {
            inventoryList.add(inventory); // Add to inventory after validating product exists
            return "Inventory added and product details fetched successfully.";
        } else {
            return "Failed to add inventory: Product does not exist.";
        }
    }

    public String updateInventory(long id, Inventory updatedInventory) {
        // Fetch product details from the Product microservice before updating
        String productResponse = inventoryServiceClient.getProductById(id);

        if (productResponse != null) {
            for (Inventory inventory : inventoryList) {
                if (inventory.getId() == id) {
                    inventory.setName(updatedInventory.getName());
                    inventory.setPrice(updatedInventory.getPrice());
                    return "Inventory updated and product details fetched successfully.";
                }
            }
            return "Inventory not found.";
        } else {
            return "Failed to update inventory: Product does not exist.";
        }
    }

    public boolean deleteInventory(long id) {
        return inventoryList.removeIf(inventory -> inventory.getId() == id);
    }
}
