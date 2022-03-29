package com.gsugambit.minecraft.rcon.exceptions;

public class RConComException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4300728038907529702L;

	public RConComException(String message, Throwable e) {
		super(message, e);
	}
}
