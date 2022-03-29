package com.gsugambit.minecraft.rcon.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gsugambit.minecraft.rcon.config.RConConfig;
import com.gsugambit.minecraft.rcon.exceptions.RConComException;
import com.gsugambit.minecraft.rcon.model.RConMessage;
import com.gsugambit.minecraft.rcon.model.RConResponse;
import com.gsugambit.minecraft.rcon.utils.ByteArrayUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RConClientCom {
	
	// move this to common place in future
	private final ByteOrder RCON_BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;

	final RConConfig config;
	final Socket clientSocket;
	final BufferedWriter writer;
	final BufferedReader reader;

	public RConClientCom(RConConfig config) {
		this.config = config;
		try {
			clientSocket = new Socket(config.getHost(), config.getPort());
			PrintWriter printer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer = new BufferedWriter(printer);
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			LOGGER.warn("Cannot connect to minecraft server: " + e.getMessage(), e);
			throw new IllegalArgumentException("cannot connect to minecraft rcon", e);
		}
	}

	public void sendMessage(RConMessage message) {
		try {
			LOGGER.info("Sending message: {} with ID: {}", message.getName(), message.getRequestId());
			writer.write(message.getProtocolMessage());
			writer.flush();
			LOGGER.info("Successfully sent message: {}", message);
			LOGGER.info("Sent {} full rcon message: {}", message.getName(), ByteArrayUtils.bytesToHex(message.getProtocolMessage()));
		} catch (IOException e) {
			String failureMessage = String.format("Failed to send: %s.", message.getName());
			throw new RConComException(failureMessage, e);
		}
	}
	
	public RConMessage receiveMessage(String messageName) {
		
		try {
			List<Byte> response = new ArrayList<>();
			
			// read length field first
			int length1 = reader.read();
			response.add((byte)length1);
			int length2 = reader.read();
			response.add((byte)length2);
			int length3 = reader.read();
			response.add((byte)length3);
			int length4 = reader.read();
			response.add((byte)length4);
			
			ByteBuffer lengthBB = ByteBuffer.wrap(
					new byte[] {
							((byte)(length1 & 0xFF)),
							((byte)(length2 & 0xFF)),
							((byte)(length3 & 0xFF)),
							((byte)(length4 & 0xFF))}
					);
			lengthBB.order(RCON_BYTE_ORDER);
			int length = lengthBB.getInt();
			LOGGER.info("Receiving message: {} with rcon length: {}", messageName, length);
			
			for(int i = 0; i < length; i++) {
				response.add((byte)reader.read());
			}
			
			LOGGER.info("Done reading message: {} with rcon length: {}", messageName, length);
			
			byte[] protocolMessage =  ByteArrayUtils.convertListToArray(response);
			RConResponse rconResponse = new RConResponse(messageName, protocolMessage);
			LOGGER.info("Received full rcon message: {}", ByteArrayUtils.bytesToHex(protocolMessage));
			return rconResponse;
		} catch(IOException e) {
			throw new RConComException("failed to receive RCon response", e);
		}
	}
}
