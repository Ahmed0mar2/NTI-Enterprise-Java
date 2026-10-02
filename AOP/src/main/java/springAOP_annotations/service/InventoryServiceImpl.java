package springAOP_annotations.service;

import org.springframework.stereotype.Service;
import springAOP_annotations.annotation.Cacheable;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Override
    @Cacheable
    public int checkStock(String sku) {
        System.out.println("Checking stock....");
        return 100;
    }

    @Override
    public void reserveStock(String sku, int qty) throws IllegalStateException {
        if (qty > 100)
            throw new IllegalStateException("Cannot reserve more than 100 items");
    }
}
