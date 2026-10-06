package com.store.repository.interfaces;

import com.store.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findByIdForUpdate(Long id);
    Optional<Product> findBySku(String sku);
    List<Product> findByCategory(String categoryName);
    List<Product> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice, String category);
    List<Product> findLowStock(int threshold);
    List<Product> findPage(int page, int size);
    Long countAll();
}
