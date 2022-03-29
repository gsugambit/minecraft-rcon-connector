package com.gsugambit.minecraft.rcon.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.gsugambit.minecraft.rcon.com.RConClientCom;
import com.gsugambit.minecraft.rcon.config.RConConfig;
import com.gsugambit.minecraft.rcon.constants.Constants;
import com.gsugambit.minecraft.rcon.constants.RConType;
import com.gsugambit.minecraft.rcon.model.RConMessage;
import com.gsugambit.minecraft.rcon.model.RConRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RConSimulator {
	final RConClientCom clientCom;
	final RConConfig config;

	public RConSimulator(RConClientCom clientCom, RConConfig config) {
		this.clientCom = clientCom;
		this.config = config;
	}

	@PostConstruct
	public void init() {
		RConMessage loginMessage = new RConRequest("loginMessage", 89, RConType.LOGIN,
				config.getPassword().getBytes());
		clientCom.sendMessage(loginMessage);
		
		RConMessage loginResponseMessage = clientCom.receiveMessage("loginResponseMessage");
		LOGGER.info("Received message: {}", loginResponseMessage);
		
		RConMessage giveBoat = new RConRequest("giveBoat", 100, RConType.RUN_COMMAND, 
				("give GSUGambit acacia_boat 1" + Constants.RCON_BYTE_TERMINATE).getBytes());
		clientCom.sendMessage(giveBoat);
		
		RConMessage giveBoatResponse = clientCom.receiveMessage("giveBoatResponse");
		LOGGER.info("Received message: {}", giveBoatResponse);
	}
}
