package com.store.repository;

import com.store.model.Category;
import com.store.model.Product;
import com.store.repository.interfaces.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
            return product;
        } else {
            return em.merge(product);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Override
    public Optional<Product> findByIdForUpdate(Long id) {
        return em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Product> findBySku(String sku) {

        return em.createQuery("SELECT p FROM Product p WHERE p.sku = :sku", Product.class).setParameter("sku", sku).getResultStream().findFirst();
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        return em.createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.name = :category", Product.class).setParameter("category", categoryName).getResultList();
    }

    @Override
    public List<Product> search(String keyword, BigDecimal minPrice, BigDecimal maxPrice, String category) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (keyword != null)
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + keyword.toLowerCase() + "%"));
        if (minPrice != null)
            predicates.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        if (maxPrice != null)
            predicates.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        if (category != null) {
            Join<Product, Category> categoryJoin = product.join("categories");
            predicates.add(cb.equal(categoryJoin.get("name"), category));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Product> findLowStock(int threshold) {
        return em.createQuery("SELECT p FROM Product p WHERE p.stock <= :threshold", Product.class).setParameter("threshold", threshold).getResultList();
    }

    @Override
    public List<Product> findPage(int page, int size) {
        int offset = page * size;
        return em.createQuery("SELECT p FROM Product p  ORDER BY p.id", Product.class).setFirstResult(offset).setMaxResults(size).getResultList();
    }
    @Override
    public Long countAll() {
        return em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
    }
}
