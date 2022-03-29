package com.gsugambit.minecraft.rcon.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.gsugambit.minecraft.rcon.utils.ObjectUtils;


public class RConResponse extends RConMessageBase {

	public RConResponse(String messageName, byte[] rconResponse) {
		super(messageName, rconResponse);
	}
	
	@Override
	public String toString() {
		return ObjectUtils.toString(this);
	}
}
