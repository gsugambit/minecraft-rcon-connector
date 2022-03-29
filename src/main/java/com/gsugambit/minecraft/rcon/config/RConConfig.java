package com.gsugambit.minecraft.rcon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class RConConfig {

	@Value("${rcon.host}")
	private String host;
	
	@Value("${rcon.password}")
	private String password;
	
	@Value("${rcon.port}")
	private int port;
	
}
