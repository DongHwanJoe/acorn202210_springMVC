package com.gura.boot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *  Spring Boot Web은 내장된 tomcat 서버다 있다.
 *  따로 tomcat서버를 잡을 필요가 없다.
 */
@SpringBootApplication
public class Boot03ControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot03ControllerApplication.class, args);
	}

}
