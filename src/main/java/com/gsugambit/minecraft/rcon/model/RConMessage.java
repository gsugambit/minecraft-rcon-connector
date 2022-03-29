package com.gsugambit.minecraft.rcon.model;

public interface RConMessage {
	
	String getName();
	
	int getRequestId();

	char[] getProtocolMessage();
}
