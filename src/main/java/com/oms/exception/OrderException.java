package com.oms.exception;

/**
 * 
 * @author asridhar2
 *
 */
public class OrderException extends RuntimeException {
	
	 /**
     * Error code to indicate the kind of error mainly business not system. Only providing getter for it to make it
     * immutable once set in constructor
     */
    private String errorCode = null;

    /**
     * Default constructor
     */
    public OrderException() {
        super();
    }

    /**
     * Constructor with two params. Should be used to convert the existing exception(cause) and pass additional message
     *
     * @param message message set by thrower
     * @param cause   cause of the exception
     */
    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with one param.  This exception should be used rarely when throwing exception. It should not be used
     * when converting existing exception received from lower layers. As it will not provide the cause of the error to
     * the reciever.
     *
     * @param message error message
     */
    public OrderException(String message) {
        super(message);
    }

    /**
     * Constructor with one param. Should be used to convert the existing exception(cause)
     *
     * @param cause cause of the exception
     */
    public OrderException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor with three parameters as explained below. Should be used to convert the existing exception(cause) and
     * pass additional message and error code
     *
     * @param message   any message to be indicated to caller
     * @param errorCode can be used by receiver to understand the kind of business error indicated by thrower and take
     *                  appropriate action.
     * @param cause     can be used by receiver to understand the kind/cause of error and take appropriate action
     */
    public OrderException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Constructor with two parameters as explained below. Should only be used when throwing and should not be used when
     * rethrowing to convert existing exception.
     *
     * @param message   any message to be indicated to caller
     * @param errorCode can be used by receiver to understand the kind of business error indicated by thrower and take
     *                  appropriate action.
     */
    public OrderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
