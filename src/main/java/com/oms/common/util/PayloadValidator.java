package com.oms.common.util;

import com.oms.business.model.OrderDataBean;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(OrderDataBean order){
		if (order.getOrderReferanceID() > 0){
			return false;
		}
		return true;
	}

}
