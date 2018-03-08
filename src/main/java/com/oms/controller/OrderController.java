package com.oms.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oms.business.dao.repo.OrderRepository;
import com.oms.business.model.OrderDataBean;
import com.oms.common.util.PayloadValidator;
import com.oms.exception.OrderException;

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
    /*@RequestMapping(value = "/Order", method = RequestMethod.POST)*/
   	public ResponseEntity<OrderDataBean> createOrder(@RequestBody OrderDataBean payload) throws OrderException{
    	logger.info("Payload to save " + payload);
    	if (!PayloadValidator.validateCreatePayload(payload)){
            throw new OrderException("Payload malformed, id must not be defined");
    	}
		return new ResponseEntity<OrderDataBean>(orderService.create(payload), HttpStatus.CREATED);
   	}
    
	/**
	 * 
	 * @param id
	 * @return Order
	 * @throws OrderException
	 */
    /*@RequestMapping(value = "/Order/{id}", method = RequestMethod.GET)*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderDataBean> findOrderById(@PathVariable("id") long id) throws OrderException{
    	logger.info("Order id to return " + id);
    	/*OrderDataBean order = orderService.findOrderById(id);*/
    	Optional<OrderDataBean> order = orderService.findOrderById(id);
    	if (order == null || order.get().getOrderId() <= 0){
            throw new OrderException("Order doesn´t exist");
    	}
		return new ResponseEntity<OrderDataBean>(orderService.findOrderById(id).get(), HttpStatus.OK);
	}
    
}
