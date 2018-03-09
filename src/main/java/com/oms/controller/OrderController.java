package com.oms.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oms.business.dao.repo.OrderRepository;
import com.oms.business.model.OrderDataBean;
import com.oms.common.util.OrderUtil;
import com.oms.exception.OrderException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/order", produces = "application/json")
public class OrderController {
 
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	/*@Autowired(required=true)
	private OrderService orderService;*/
	
	@Autowired
    private OrderRepository orderService;
	
	/**
	 * createOrder 
	 * @param payload
	 * @return  Order referance Number
	 * @throws OrderException
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
   	public ResponseEntity<OrderDataBean> createOrder(@RequestBody OrderDataBean payload) throws OrderException{
    	logger.info("Payload to save " + payload);
    	if (!OrderUtil.validateOrderPayload(payload)){
            throw new OrderException("Customer should have minumum Order greater than Zero");
    	}
		return new ResponseEntity<OrderDataBean>(orderService.create(payload), HttpStatus.CREATED);
   	}
    
   /**
	 * 
	 * @param id
	 * @return Order
	 * @throws OrderException
	 */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderDataBean> findOrderById(@PathVariable("id") long id) throws OrderException{
    	logger.info("Order id to return " + id);
    	/*OrderDataBean order = orderService.findOrderById(id);*/
    	Optional<OrderDataBean> order = orderService.findOrderById(id);
    	if (order.isPresent()) {
    		return new ResponseEntity<OrderDataBean>(orderService.findOrderById(id).get(), HttpStatus.OK);
		}
    	else{
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
		
	}
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		boolean wasDeleted = orderService.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<OrderDataBean> updateOrder(@PathVariable Long id, @RequestBody OrderDataBean updatedOrder) {
		boolean wasUpdated = orderService.update(id, updatedOrder);
		
		if (wasUpdated) {
			return findOrderById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    
    
}
