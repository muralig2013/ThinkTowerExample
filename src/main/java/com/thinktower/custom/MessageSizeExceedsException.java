package com.thinktower.custom;

public class MessageSizeExceedsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageSizeExceedsException(String s){  
		  super(s);  
	}  
	
	@Override
	public String toString(){
		return "Message size exceeds 249 characters";		
	}
}
