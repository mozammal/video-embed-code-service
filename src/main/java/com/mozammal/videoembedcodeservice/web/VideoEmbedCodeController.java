package com.mozammal.videoembedcodeservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mozammal.videoembedcodeservice.service.VideoEmbedCodeService;

import lombok.AllArgsConstructor;

import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class VideoEmbedCodeController {
    private final VideoEmbedCodeService videoEmbedCodeService;

    // http -v GET "localhost:8080/embed-code?url=https://www.youtube.com/watch?v=GNU6Ue9HUJE"
    @GetMapping("/embed-code")
    public Mono<String> getVideoEmbedCode(@RequestParam String url) {
        return videoEmbedCodeService.urlToEmbedCode(url);
    }
}
