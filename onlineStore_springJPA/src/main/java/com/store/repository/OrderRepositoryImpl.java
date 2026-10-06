package com.store.repository;

import com.store.model.Order;
import com.store.model.enums.OrderStatus;
import com.store.repository.interfaces.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public Optional<Order> findByIdWithItems(Long id) {

        return em.createQuery("""
                SELECT DISTINCT o FROM Order o
                LEFT JOIN FETCH o.items i
                LEFT JOIN FETCH i.product
                LEFT JOIN FETCH o.customer
                WHERE o.id = :id
                """, Order.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<Order> findByCustomer(Long customerId) {
        return em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id", Order.class)
                .setParameter("id",customerId)
                .getResultList();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return em.createQuery("SELECT o FROM Order o WHERE o.status = :status", Order.class)
                .setParameter("status",status)
                .getResultList();
    }
}
