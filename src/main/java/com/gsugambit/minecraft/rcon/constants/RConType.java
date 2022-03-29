package com.gsugambit.minecraft.rcon.constants;

public enum RConType {

	LOGIN(3),
	RUN_COMMAND(2),
	MULTI_PACKET(0);
	
	private final int value;
	
	private RConType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
