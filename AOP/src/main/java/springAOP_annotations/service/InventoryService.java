package springAOP_annotations.service;

public interface InventoryService {
    int checkStock(String sku);
    void reserveStock(String sku, int qty) throws IllegalStateException;
}
