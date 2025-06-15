package com.dreamhire.talkbbungchi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TalkbbungchiApplication {
	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load(); // .env 파일을 로딩
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(TalkbbungchiApplication.class, args);
	}
}
