package com.oms.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.business.dao.repo.OrderRepo;
import com.oms.business.domain.OrderDO;
import com.oms.business.model.OrderDataBean;
import com.oms.common.util.ObjectMapper;

/**
 * 
 * @author asridhar2
 *
 */
@Service("orderService")
public class OrderServiceImpl extends OrderBaseService implements OrderService{

	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public OrderDataBean findOrderById(long id) {
		OrderDO orderDO = orderRepo.findOne(id);
		OrderDataBean order = mapper.map(orderDO,OrderDataBean.class);
		return order;
	}

	@Override
	public OrderDataBean create(OrderDataBean order) {
		OrderDO orderDO= mapper.map(order,OrderDO.class);
		orderDO = orderRepo.save(orderDO);
		return order;
	}
}
