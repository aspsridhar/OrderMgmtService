package com.oms.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.oms.business.model.OrderDataBean;
/**
 * PayloadValidatorTest
 * @author asridhar2
 *
 */
public class OrderUtilTest {

	@Test
	public void validatePayLoad() {
		OrderDataBean dataBean = new OrderDataBean(1002L, 43.23,20, "Bricks", "Delevered", true, "Order total of 20",
				-1);
		assertEquals(true, OrderUtil.validateOrderPayload(dataBean));
	}
	
	@Test
	public void validateInvalidPayLoad() {
		OrderDataBean dataBean = new OrderDataBean(1002L, 43.23,20, "Bricks", "Delevered", true, "Order total of 20",
				1234578);
		assertEquals(false, OrderUtil.validateOrderPayload(dataBean));
	}
	
	

}
