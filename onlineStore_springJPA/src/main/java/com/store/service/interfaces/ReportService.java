package com.store.service.interfaces;

import com.store.dto.CategoryRevenue;
import com.store.dto.CustomerSpend;
import com.store.dto.MonthlySales;
import com.store.model.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<CategoryRevenue> revenueByCategory();
    List<CustomerSpend> topCustomers(int limit);
    Map<OrderStatus, Long> ordersPerStatus();
    List<String> productsNeverOrdered();
    List<MonthlySales> monthlySales(int year);
    int applyDiscount(String category, double percent);
}
