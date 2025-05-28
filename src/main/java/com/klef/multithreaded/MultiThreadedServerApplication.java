package com.klef.multithreaded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.klef.multithreaded.config.Config1;

@SpringBootApplication
public class MultiThreadedServerApplication {

	public static void main(String[] args) {
		Config1 c = new Config1();
		c.createThreads();
		SpringApplication.run(MultiThreadedServerApplication.class, args);
	}

}
