package com.oms.business.dao.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.oms.business.model.OrderDataBean;

/**
 * InMemoryRepository
 * @author asridhar2
 *
 * @param <T>
 */
public abstract class InMemoryRepository<T extends OrderDataBean> {
    @Autowired
    private IdGenerator idGenerator;
    private List<T> elements = Collections.synchronizedList(new ArrayList<>());
    
    /**
     * create
     * @param element
     * @return
     */
    public T create(T element) {
        elements.add(element);
        element.setOrderReferanceID(idGenerator.getNextId());
        return element;
    }
   
    /**
     * delete
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        return elements.removeIf(element -> Long.valueOf(element.getOrderId()).equals(id));
    }
    
    /**
     * 
     * @return
     */
    public List<T> findAll() {
        return elements;
    }
    public Optional<T> findOrderById(Long id) {
        return elements.stream().filter(e -> Long.valueOf(e.getOrderId()).equals(id)).findFirst();
    }
    
}
