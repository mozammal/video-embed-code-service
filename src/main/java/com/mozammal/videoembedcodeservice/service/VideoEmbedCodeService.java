package com.mozammal.videoembedcodeservice.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mozammal.videoembedcodeservice.model.OembedVideoResponse;

import lombok.extern.slf4j.Slf4j;

import reactor.core.publisher.Mono;

@Slf4j
@Service
public class VideoEmbedCodeService {
    private static final Pattern YOUTUBE_URL_FORMAT =
        Pattern.compile("(?<=youtu.be/|watch\\?v=|/videos/|v/|e/|embed\\/)[^#\\&\\?]*");
    private static final String YOUTUBE_OEMBED_URL = "https://www.youtube.com/oembed?url=";
    private final WebClient     webClient;

    public VideoEmbedCodeService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<String> urlToEmbedCode(String videoUrl) {
        Matcher matcher = YOUTUBE_URL_FORMAT.matcher(videoUrl);

        if (!matcher.find()) {
            return Mono.empty();
        }

        String youtubeOembedUri = YOUTUBE_OEMBED_URL + videoUrl + "&format=json";

        return webClient.get()
                        .uri(youtubeOembedUri)
                        .retrieve()
                        .bodyToMono(OembedVideoResponse.class)
                        .map(response -> response.iframeToUrl(response.getHtml()));
    }
}