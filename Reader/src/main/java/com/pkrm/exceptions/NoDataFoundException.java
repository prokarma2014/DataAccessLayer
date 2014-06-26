package com.pkrm.exceptions;

/**
 * 
 * @author Prokarma
 *
 */
public class NoDataFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	 /**
     * Construct a default action  exception.
     */
    public NoDataFoundException()
    {
    }

    /**
     * Construct an action  exception with the specified message.
     * @param message The exception message.
     */
    public NoDataFoundException(final String message) 
    {
	super(message);
    }

    /**
     * Construct an action  exception with the associated cause.
     * @param cause The associated cause.
     */
    public NoDataFoundException(final Throwable cause)
    {
	super(cause);
    }

    /**
     * Construct an action  exception with the specified message and associated cause.
     * @param message The exception message.
     * @param cause The associated cause.
     */
    public NoDataFoundException(String message, Throwable cause)
    {
	super(message, cause);
    }

}
