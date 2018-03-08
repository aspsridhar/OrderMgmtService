package com.oms.business.service;

import javax.persistence.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import com.oms.exception.OrderException;


/**
 * BaseService class for common methods that can be used by subclasses.
 *
 * @author asridhar2
 */
public class OrderBaseService {

    private final Logger LOG = LoggerFactory.getLogger(OrderBaseService.class);

    /**
     * this should be some public constant valid for all use cases value can be 'eddy.concurrent.error' can be used as
     * key in the properties file to get the message in Mbean
     */
    public static final String CONCURRENT_MODIFICATION = "OMS.error.concurent";

    /**
     * this should be public constant valid for all use cases value 'rdms.error.system' can be used as key in the
     * properties file to get the generic message in Mbean or use case specific message can be used.
     */
    public static final String SYSTEM_ERROR = "OMS.error.system";

    /**
     * logs and handles the error from spring data api
     *
     * @param dataAccessException DataAccessException thrown by Spring Data
     * @throws OrderException with appropriate code in the message. The code can be used to do custom handling or as key
     *                       in the properties file to show the message
     */
    protected void handleSpringDataException(DataAccessException dataAccessException) {
        LOG.error("Spring Data related error...", dataAccessException);

        Throwable cause = dataAccessException.getMostSpecificCause();

        if (cause instanceof OptimisticLockException
        		|| dataAccessException instanceof ObjectOptimisticLockingFailureException) {
            throw new OrderException("Concurrent modification exception",CONCURRENT_MODIFICATION, dataAccessException);
        } else {
            handleSystemException(dataAccessException);
        }

    }
    
    /**
     * Logs and handles generic errors
     *
     * @param e DataAccessException thrown by Spring Data
     * @throws OrderException throws this exception with appropriate code in the message. The code can be used to do
     *                       custom handling or as key in the properties file to show generic message
     */
    protected void handleOrderException(OrderException e) {
        LOG.error("OrderException ", e);
        throw new OrderException(e.getMessage());
    }

    /**
     * Logs and handles generic errors
     *
     * @param e DataAccessException thrown by Spring Data
     * @throws OrderException throws this exception with appropriate code in the message. The code can be used to do
     *                       custom handling or as key in the properties file to show generic message
     */
    protected void handleSystemException(Exception e) {
        LOG.error("System error", e);
        throw new OrderException("System Error",SYSTEM_ERROR, e);
    }
}
