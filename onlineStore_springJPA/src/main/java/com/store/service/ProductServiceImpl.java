package com.store.service;

import com.store.exception.ProductNotFoundException;
import com.store.model.Product;
import com.store.repository.interfaces.ProductRepository;
import com.store.service.interfaces.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product addProduct(String sku, String name, BigDecimal price, int stock) {
        if (price == null || price.signum() < 0 || stock < 0) {
            throw new IllegalArgumentException("Price and stock cannot be negative");
        }
        Product product = new Product();
        product.setSku(sku);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product restock(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be greater than zero");
        }
        Product product = getProduct(productId);
        product.setStock(product.getStock() + quantity);
        return product;
    }

    @Override
    @Transactional
    public Product changePrice(Long productId, BigDecimal newPrice) {
        if (newPrice == null || newPrice.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Product product = getProduct(productId);
        product.setPrice(newPrice);
        return product;
    }

    private Product getProduct(Long productId) {
        return productRepository.findByIdForUpdate(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
