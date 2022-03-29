package com.gsugambit.minecraft.rcon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinecraftApplication {
	static char nullCharacter = '\u0000';

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		SpringApplication.run(MinecraftApplication.class, args);
//		Socket clientSocket = new Socket("127.0.0.1", 31001);
//		PrintWriter printer = new PrintWriter(clientSocket.getOutputStream(), true);
//		BufferedWriter out = new BufferedWriter(printer);
//		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//		
//		Thread.sleep(2000);
//		
//		int loginRequestId = 0x13;
//		int loginType = 3;
//		byte[] loginPayload = "password".getBytes();
//		byte[] loginTermPayload = combineByteArray(loginPayload, new byte[] {(byte) nullCharacter});
//		
//		// sending login message
//		sendMessage(out, loginRequestId, loginType, loginTermPayload);
//		//Thread.sleep(2000);
//		
//		// receive login response
//		byte[] loginResponse = receiveMessage(in);
//		System.out.println("loginResponse: " + bytesToHex(loginResponse));
//		//Thread.sleep(2000);
//		
//		int requestId = 1;
//		int type = 2;
//		byte[] payload = "time set 0".getBytes();
//		byte[] nullTermPayload = combineByteArray(payload, new byte[] {(byte) nullCharacter});
//		// setting day time
//		sendMessage(out, requestId, type, nullTermPayload);
//		
//		// receive login response
//		byte[] timeResponse = receiveMessage(in);
//		System.out.println("timeResponse: " + bytesToHex(timeResponse));
//		interpret(timeResponse);
//		
//		int giveRequestId = 2;
//		int giveType = 2;
//		byte[] givePayload = "give GSUGambit acacia_boat 1".getBytes();
//		byte[] givePayloadTerm = combineByteArray(givePayload, new byte[] {(byte) nullCharacter});
//		// giving boat
//		sendMessage(out, giveRequestId, giveType, givePayloadTerm);
//		
//		byte[] giveResponse = receiveMessage(in);
//		System.out.println("giveResponse: " + bytesToHex(giveResponse));
//		interpret(giveResponse);
//		
//		clientSocket.close();
//	}
//	
//	public static byte[] receiveMessage(BufferedReader reader) throws IOException {
//		List<Byte> response = new ArrayList<>();
//		
//		// read length field first
//		int length1 = reader.read();
//		response.add((byte)length1);
//		int length2 = reader.read();
//		response.add((byte)length2);
//		int length3 = reader.read();
//		response.add((byte)length3);
//		int length4 = reader.read();
//		response.add((byte)length4);
//		byte[] packetLength = new byte[] {(byte)length1, (byte)length2, (byte)length3, (byte)length4};
//		System.out.println("header length: " + bytesToHex(packetLength));
//		
//		for(int i = 0; i < length1; i++) {
//			response.add((byte)reader.read());
//		}
//		
//		System.out.println("done reading " + length1 + " characters");
//		return convertListToArray(response);
	}
	
//	public static void sendMessage(BufferedWriter out, int requestId, int type, byte[] payload) throws IOException {
//		List<Byte> messageToSend = new ArrayList<>();
//		messageToSend.add(((byte)(requestId & 0xFF)));
//		messageToSend.add((byte)0);
//		messageToSend.add((byte)0);
//		messageToSend.add((byte)0);
//		messageToSend.add(((byte)(type & 0xFF)));
//		messageToSend.add((byte)0);
//		messageToSend.add((byte)0);
//		messageToSend.add((byte)0);
//		
//		for(byte b : payload) {
//			messageToSend.add(b);
//		}
//		
//		messageToSend.add((byte)0);
//		
//		int length = messageToSend.size();
//		byte[] messageHeader = new byte[] {(byte)length, (byte)0, (byte)0, (byte)0};
//		
//		byte[] totalMessage = combineByteArray(messageHeader, convertListToArray(messageToSend));
//		char[] totalMessageChar = convertByteArrayToCharArray(totalMessage);
//		out.write(totalMessageChar);
//		out.flush();
//
//		System.out.println("sent message: " + bytesToHex(totalMessage));
//		System.out.println("sent char message: " + charsToHex(totalMessageChar));
//	}
//	
//	
//	
//	
//	
//	public static void interpret(byte[] response) {
//		System.out.println(new MinecraftResponse(response).toString());
//	}
}
