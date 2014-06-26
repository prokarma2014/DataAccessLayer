package com.pkrm.exceptions;

/**
 * 
 * @author Prokarma
 *
 */
public class DataBaseConnectionException extends Exception  {

	private static final long serialVersionUID = 7598121942658974363L;

	
	 /**
    * Construct a default action  exception.
    */
   public DataBaseConnectionException()
   {
   }

   /**
    * Construct an action  exception with the specified message.
    * @param message The exception message.
    */
   public DataBaseConnectionException(final String message) 
   {
	super(message);
   }

   /**
    * Construct an action  exception with the associated cause.
    * @param cause The associated cause.
    */
   public DataBaseConnectionException(final Throwable cause)
   {
	super(cause);
   }

   /**
    * Construct an action  exception with the specified message and associated cause.
    * @param message The exception message.
    * @param cause The associated cause.
    */
   public DataBaseConnectionException(String message, Throwable cause)
   {
	super(message, cause);
   }

}
