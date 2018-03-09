package com.oms.business.dao.repo;

import org.springframework.stereotype.Repository;

import com.oms.business.model.OrderDataBean;

@Repository
public class OrderRepository extends InMemoryRepository<OrderDataBean> {
	
    protected void updateIfExists(OrderDataBean original, OrderDataBean updated) {
        original.setDescription(updated.getDescription());
        original.setPrice(updated.getPrice());
        original.setCompleted(updated.isCompleted());
    }

	
}