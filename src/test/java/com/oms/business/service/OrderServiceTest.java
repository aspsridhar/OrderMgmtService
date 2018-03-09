package com.oms.business.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oms.business.dao.repo.OrderRepo;
import com.oms.business.domain.OrderDO;
import com.oms.business.model.OrderDataBean;

@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {
	
	@Mock
	private OrderRepo orderRepo;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testFindOrderById(){
		OrderDO orderDO = new OrderDO(1002L,10.10,20,"Bricks", "Delevered", true, "Order total of 20",
				1234578);
		when(orderRepo.findOne(1002L)).thenReturn(orderDO);
		OrderDataBean result = orderService.findOrderById(1002L);
		assertEquals(1234578, result.getOrderReferanceID());
		assertEquals("Order total of 20", result.getDescription());
		assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void create(){
		OrderDO orderDO = new OrderDO(1001L, 10.10, 20,"Bricks", "In Process", false, "Order total of 20",
				4234578);
		OrderDataBean orderBean = new OrderDataBean(1001L, 10.10,20,"Bricks", "In Process", false, "Order total of 20",
				4234578);
		when(orderRepo.save(orderDO)).thenReturn(orderDO);
		OrderDataBean result = orderService.create(orderBean);
		assertEquals(4234578, result.getOrderReferanceID());
		assertEquals(20, result.getNoOfBricks());
		assertEquals(false, result.isCompleted());
	}
	
	
	

}

