package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public InventoryService(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    private List<Inventory> inventoryList = new ArrayList<>();

    public List<Inventory> getAllInventories() {
        return inventoryList;
    }

    public String getProductInventoryById(long id) {
        Product product = inventoryServiceClient.getProductById(id);
        Inventory inventory = inventoryList.stream().filter(inventory1 -> inventory1.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Inventory with ID " + id + " not found"));

        ProductInventory productInventory = new ProductInventory(
                product.getId(),
                product.getName(),
                product.getPrice(),
                inventory.getBrand(),
                inventory.getQuantity()
        );
        return "Product details: \n" + productInventory.toString();
    }

    public Inventory createInventory(Inventory inventory) {
        for (Inventory existingInventory : inventoryList) {
            if (existingInventory.getId() == inventory.getId()) {
                throw new IllegalArgumentException("Inventory with ID " + inventory.getId() + " already exists");
            }
        }
        inventoryList.add(inventory);
        return inventory;
    }

    public Inventory editInventory(long id, Inventory editInventory) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getId() == id) {
                inventory.setBrand(editInventory.getBrand());
                inventory.setQuantity(editInventory.getQuantity());
                return inventory;
            }
        }
        throw new IllegalArgumentException("Inventory with ID " + id + " not found");
    }

    public Inventory deleteInventory(long id) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getId() == id) {
                inventoryList.remove(inventory);
                return inventory;
            }
        }
        throw new IllegalArgumentException("Inventory with ID " + id + " not found");
    }
}