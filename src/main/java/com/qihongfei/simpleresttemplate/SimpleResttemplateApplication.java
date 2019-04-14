package com.qihongfei.simpleresttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@Slf4j
public class SimpleResttemplateApplication implements ApplicationRunner {

	public static void main(String[] args) {
//		SpringApplication.run(SimpleResttemplateApplication.class, args);
		new SpringApplicationBuilder()
				.sources(SimpleResttemplateApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args);

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Autowired
	private RestTemplate restTemplate;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		URI uri=URI.create("https://www.baidu.com");
		ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
		log.info(forEntity.getBody());
	}
}
