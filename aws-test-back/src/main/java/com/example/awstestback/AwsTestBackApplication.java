package com.example.awstestback;

import com.example.awstestback.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
// 환경변수 파일 위치 잡기
@PropertySource(
		value = {"classpath:env/env.yml", "classpath:env/env-pro.yml"},
		factory = EnvConfig.class
)
public class AwsTestBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsTestBackApplication.class, args);
	}

}
