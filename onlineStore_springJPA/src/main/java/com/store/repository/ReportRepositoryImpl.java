package com.store.repository;

import com.store.dto.CategoryRevenue;
import com.store.dto.CustomerSpend;
import com.store.dto.MonthlySales;
import com.store.model.enums.OrderStatus;
import com.store.repository.interfaces.ReportRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategoryRevenue> revenueByCategory() {
        return em.createQuery("""
                SELECT new com.store.dto.CategoryRevenue(c.name,
                    SUM(i.unitPrice * i.quantity))
                FROM OrderItem i
                JOIN i.order o
                JOIN i.product p
                JOIN p.categories c
                WHERE o.status IN (com.store.model.enums.OrderStatus.PAID,
                                   com.store.model.enums.OrderStatus.SHIPPED)
                GROUP BY c.name
                ORDER BY c.name
                """, CategoryRevenue.class).getResultList();
    }

    @Override
    public List<CustomerSpend> topCustomers(int limit) {
        return em.createQuery("""
                SELECT new com.store.dto.CustomerSpend(o.customer.name,
                    SUM(i.unitPrice * i.quantity))
                FROM Order o JOIN o.items i
                WHERE o.status IN (com.store.model.enums.OrderStatus.PAID,
                                   com.store.model.enums.OrderStatus.SHIPPED)
                GROUP BY o.customer.id, o.customer.name
                ORDER BY SUM(i.unitPrice * i.quantity) DESC
                """, CustomerSpend.class).setMaxResults(limit).getResultList();
    }

    @Override
    public Map<OrderStatus, Long> ordersPerStatus() {
        Map<OrderStatus, Long> result = new EnumMap<>(OrderStatus.class);
        for (OrderStatus status : OrderStatus.values()) {
            result.put(status, 0L);
        }
        for (Object[] row : em.createQuery(
                "SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status", Object[].class)
                .getResultList()) {
            result.put((OrderStatus) row[0], (Long) row[1]);
        }
        return result;
    }

    @Override
    public List<String> productsNeverOrdered() {
        return em.createQuery("""
                SELECT p.name FROM Product p
                WHERE NOT EXISTS (SELECT i.id FROM OrderItem i WHERE i.product = p)
                ORDER BY p.name
                """, String.class).getResultList();
    }

    @Override
    public List<MonthlySales> monthlySales(int year) {
        return em.createQuery("""
                SELECT new com.store.dto.MonthlySales(
                    MONTH(o.orderedAt), SUM(i.unitPrice * i.quantity))
                FROM Order o JOIN o.items i
                WHERE YEAR(o.orderedAt) = :year
                  AND o.status IN (com.store.model.enums.OrderStatus.PAID,
                                   com.store.model.enums.OrderStatus.SHIPPED)
                GROUP BY MONTH(o.orderedAt)
                ORDER BY MONTH(o.orderedAt)
                """, MonthlySales.class).setParameter("year", year).getResultList();
    }

    @Override
    public int applyDiscount(String category, double percent) {
        Query query = em.createQuery("""
                UPDATE Product p SET p.price = p.price * (1 - :percent)
                WHERE EXISTS (SELECT c FROM p.categories c WHERE c.name = :category)
                """);
        query.setParameter("percent", percent / 100d);
        query.setParameter("category", category);
        int updated = query.executeUpdate();
        em.clear();
        return updated;
    }
}
