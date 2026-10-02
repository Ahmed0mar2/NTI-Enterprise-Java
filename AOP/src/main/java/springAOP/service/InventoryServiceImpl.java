package springAOP.service;

public class InventoryServiceImpl implements InventoryService{
    @Override
    public int checkStock(String sku) {

        return 0;
    }

    @Override
    public void reserveStock(String sku, int qty) throws IllegalStateException {
        if(qty > 100 )
            throw new IllegalStateException();
    }
}
