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
            }
        }
        return null;
    }

    public String getProductInventoryById(long id) {
        Product product = inventoryServiceClient.getProductById(id);
        Inventory inventory = inventoryList.stream().filter(inventory1 -> inventory1.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Inventory not found for id: " + id));

        ProductInventory productInventory = new ProductInventory(
                product.getId(),
                product.getName(),
                product.getPrice(),
                inventory.getBrand(),
                inventory.getQuantity()
        );
        return "Here are the details of your product: \n" + productInventory.toString();
    }


}