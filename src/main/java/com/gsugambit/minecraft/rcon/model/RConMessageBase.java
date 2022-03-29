package com.gsugambit.minecraft.rcon.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gsugambit.minecraft.rcon.utils.ByteArrayUtils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import static com.gsugambit.minecraft.rcon.constants.Constants.*;

public abstract class RConMessageBase implements RConMessage {
	
	@JsonIgnore
	private char[] protocolMessage;
	
	@Getter
	protected String name;
	@Getter
	protected int length;
	@Getter
	protected int requestId;
	@Getter
	protected int type;
	protected byte[] payload;
	protected byte pad;
	
	public RConMessageBase(String name, int requestId, int type, byte[] payload) {
		this.name = name;
		this.requestId = requestId;
		this.type = type;
		this.payload = payload;
		
		ByteBuffer requestIdBB = ByteBuffer.allocate(4);
		requestIdBB.order(RCON_BYTE_ORDER);
		requestIdBB.putInt(requestId);
		
		ByteBuffer typeBB = ByteBuffer.allocate(4);
		typeBB.order(RCON_BYTE_ORDER);
		typeBB.putInt(type);
		
		// 8 is request+type + payload + 1byte pad
		final byte[] body = new byte[8+payload.length +1];
		body[body.length-1] = RCON_BYTE_TERMINATE;
		length = body.length;
		
		ByteBuffer lengthBB = ByteBuffer.allocate(4);
		lengthBB.order(RCON_BYTE_ORDER);
		lengthBB.putInt(length);
		
		
		byte[] protocolMessageBytes = ByteArrayUtils.combineByteArray(
				lengthBB.array(),
				requestIdBB.array(),
				typeBB.array(),
				payload,
				new byte[] { RCON_BYTE_TERMINATE }
				);
		protocolMessage = ByteArrayUtils.convertByteArrayToCharArray(protocolMessageBytes);
	}
	
	public RConMessageBase(String name, byte[] rconMessage) {
		this.name = name;
		this.protocolMessage = ByteArrayUtils.convertByteArrayToCharArray(rconMessage);
		
		ByteBuffer buffer = ByteBuffer.wrap(rconMessage);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		length = buffer.getInt(0);
		requestId = buffer.getInt(4);
		type = buffer.getInt(8);
		
		payload = new byte[rconMessage.length - 13];
		for(int i = 12; i < rconMessage.length -1; i++) {
			payload[i-12] = rconMessage[i];
		}
		pad = rconMessage[rconMessage.length -1];
	}
	
	public String getPayloadAsString() {
		return payload == null ? "" : new String(payload);
	}
	
	public char[] getProtocolMessage() {
		return protocolMessage;
	}
}
