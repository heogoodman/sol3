package com.example.practiceMyself;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PracticeMyselfApplication {

	public static void main(String[] args) {

//		SpringApplication.run(PracticeMyselfApplication.class, args);
		SpringApplication application = new SpringApplication(PracticeMyselfApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

	// 세터와 게터로 반복을 해야하는 것을 modelMapper가 대신 해주기 때문에 Bean으로 설정해서 편하게 사용
	// 여기다 하면 어디서든지 사용가능
	// 여기 아니면 config 파일을 생성해서 사용 가능
	@Bean
	public ModelMapper modelMapper () {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

}
