package com.oms.common.util;

import com.oms.business.domain.OrderDO;
import com.oms.business.model.OrderDataBean;

public class OrderUtil {
	
	/**
	 * validateOrderPayload
	 * @param order
	 * @return boolean
	 */
	public static boolean validateOrderPayload(OrderDataBean order){
		if (order.getNoOfBricks() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * convertDO
	 * @param orderDO
	 * @param order
	 */
	public static OrderDataBean convertToOrderDataBean(OrderDO orderDO) {
		
		OrderDataBean orderBean= new OrderDataBean();
		orderBean.setOrderId(orderDO.getOrderId());
		orderBean.setNoOfBricks(orderDO.getNoOfBricks());
		orderBean.setCategory(orderDO.getCategory());
		orderBean.setCompleted(orderDO.isCompleted());
		orderBean.setDescription(orderDO.getDescription());
		orderBean.setOrderReferanceID(orderDO.getOrderReferanceID());
		
		return orderBean;
	}
	
	/**
	 * convertDO
	 * @param orderDO
	 * @param order
	 */
	public static OrderDO convertToOrderDO(OrderDataBean orderBean) {
		
		OrderDO orderDO= new OrderDO();
		orderDO.setOrderId(orderBean.getOrderId());
		orderDO.setNoOfBricks(orderBean.getNoOfBricks());
		orderDO.setCategory(orderBean.getCategory());
		orderDO.setCompleted(orderBean.isCompleted());
		orderDO.setDescription(orderBean.getDescription());
		orderDO.setOrderReferanceID(orderBean.getOrderReferanceID());
		
		return orderDO;
	}

}
