package com.qihongfei.simpleresttemplate;

import com.qihongfei.simpleresttemplate.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //URI uri=URI.create("https://www.baidu.com");
        URI uri = UriComponentsBuilder.fromUriString("http://www.baidu.com").build().toUri();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        log.info(forEntity.getBody());

        URI nameUri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/?name={name}")
                .build("qihongfei");
        RequestEntity requestEntity = RequestEntity.get(nameUri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .build();
        ResponseEntity<Coffee> exchange = restTemplate.exchange(requestEntity, Coffee.class);
        log.info("exchange response : {}", exchange);


    }
}
