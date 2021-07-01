package com.mozammal.videoembedcodeservice.exception;

import java.io.IOException;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@Order(-2)
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {
    private ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        if (throwable instanceof IOException) {
            return getVoidMono(serverWebExchange, HttpStatus.BAD_REQUEST, new ErrorMessage("Bad request"));
        } else if (throwable instanceof WebClientResponseException) {
            WebClientResponseException exception = (WebClientResponseException) throwable;
            exception.getStatusCode();
            return getVoidMono(serverWebExchange,
                               ((WebClientResponseException) throwable).getStatusCode(),
                               new ErrorMessage("Response from upstream throw exception"));
        }

        return getVoidMono(serverWebExchange, HttpStatus.INTERNAL_SERVER_ERROR, new ErrorMessage("Unknown exception"));
    }

    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, HttpStatus httpStatus,
                                   ErrorMessage errorMessage) {
        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        serverWebExchange.getResponse().setStatusCode(httpStatus);
        DataBuffer dataBuffer;

        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(errorMessage));
        } catch (JsonProcessingException e) {
            dataBuffer = bufferFactory.wrap("".getBytes());
        }

        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }
}