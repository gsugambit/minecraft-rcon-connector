package com.gsugambit.minecraft.rcon.model;

import com.gsugambit.minecraft.rcon.constants.RConType;
import com.gsugambit.minecraft.rcon.utils.ObjectUtils;

public class RConRequest extends RConMessageBase {

	public RConRequest(String name, int requestId, RConType type, byte[] payload) {
		super(name, requestId, type.getValue(), payload);
	}

	@Override
	public String toString() {
		return ObjectUtils.toString(this);
	}
}
