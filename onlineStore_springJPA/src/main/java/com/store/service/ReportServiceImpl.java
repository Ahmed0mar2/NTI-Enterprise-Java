package com.store.service;

import com.store.dto.CategoryRevenue;
import com.store.dto.CustomerSpend;
import com.store.dto.MonthlySales;
import com.store.model.enums.OrderStatus;
import com.store.repository.interfaces.ReportRepository;
import com.store.service.interfaces.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryRevenue> revenueByCategory() {
        return reportRepository.revenueByCategory();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerSpend> topCustomers(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }
        return reportRepository.topCustomers(limit);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<OrderStatus, Long> ordersPerStatus() {
        return reportRepository.ordersPerStatus();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> productsNeverOrdered() {
        return reportRepository.productsNeverOrdered();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MonthlySales> monthlySales(int year) {
        return reportRepository.monthlySales(year);
    }

    @Override
    @Transactional
    public int applyDiscount(String category, double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return reportRepository.applyDiscount(category, percent);
    }
}
