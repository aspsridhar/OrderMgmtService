package com.oms.business.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oms.business.domain.OrderDO;

/**
 * 
 * @author asridhar2
 *
 */
public interface OrderRepo extends JpaRepository<OrderDO, Long> {

}
