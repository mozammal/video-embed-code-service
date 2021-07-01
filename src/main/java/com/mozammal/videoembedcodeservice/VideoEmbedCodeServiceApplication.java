package com.mozammal.videoembedcodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;

@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)
public class VideoEmbedCodeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoEmbedCodeServiceApplication.class, args);
    }
}
