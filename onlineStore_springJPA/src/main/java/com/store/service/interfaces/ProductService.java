package com.store.service.interfaces;

import com.store.model.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product addProduct(String sku, String name, BigDecimal price, int stock);
    Product restock(Long productId, int quantity);
    Product changePrice(Long productId, BigDecimal newPrice);
}
