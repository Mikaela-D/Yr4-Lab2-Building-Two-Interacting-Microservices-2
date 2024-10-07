package ie.atu.yr4lab2buildingtwointeractingmicroservices2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryServiceClient inventoryServiceClient;

    private List<Inventory> inventoryList = new ArrayList<>(); // ensure it's initialized

    @Autowired
    public InventoryService(InventoryServiceClient inventoryServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public List<Inventory> getAllInventories() {
        if (inventoryList.isEmpty()) {
            throw new RuntimeException("No inventories found");
        }
        return inventoryList;
    }

    public Inventory addInventory(Inventory inventory) {
        inventoryList.add(inventory);
        return inventory;
    }

    public Inventory updateInventory(long id, Inventory updatedInventory) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getId() == id) {
                inventory.setBrand(updatedInventory.getBrand());
                inventory.setQuantity(updatedInventory.getQuantity());
                return inventory;
            }
        }
        return updatedInventory;
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

    public String getProductInventoryById(long id) {
        Inventory inventory = inventoryList.stream()
                .filter(inv -> inv.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Inventory with ID " + id + " not found"));

        Product product = inventoryServiceClient.getProductById(id);
        ProductInventory productInventory = new ProductInventory(
                product.getId(),
                product.getName(),
                product.getPrice(),
                inventory.getBrand(),
                inventory.getQuantity()
        );

        return "Product details: \n" + productInventory.toString();
    }


}