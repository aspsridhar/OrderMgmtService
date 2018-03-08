package com.oms.business.service;

import com.oms.business.model.OrderDataBean;

/**
 * 
 * @author asridhar2
 *
 */
public interface OrderService {
	
	public OrderDataBean findOrderById(long id);
	public OrderDataBean create(OrderDataBean order);

}
