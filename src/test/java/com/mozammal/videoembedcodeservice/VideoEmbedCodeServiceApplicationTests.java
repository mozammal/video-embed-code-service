package com.mozammal.videoembedcodeservice;

import com.mozammal.videoembedcodeservice.service.VideoEmbedCodeService;
import com.mozammal.videoembedcodeservice.web.VideoEmbedCodeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.support.ServerRequestWrapper;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebFluxTest
class VideoEmbedCodeServiceApplicationTests {

  @MockBean
  @Autowired private VideoEmbedCodeController codeController;

  @Autowired private WebTestClient webTestClient;

  @Test
  void contextLoads() {}

  @Test
  void testShouldReturnValidEmbedCode() {

    String videoUrl = "https://www.youtube.com/watch?v=GNU6Ue9HUJE";
    String videoEmbedCode = "https://www.youtube.com/embed/GNU6Ue9HUJE";
    Mockito.when(codeController.getVideoEmbedCode(videoUrl)).thenReturn(Mono.just(videoEmbedCode));

    webTestClient
        .get()
        .uri("/embed-code?url=" + "https://www.youtube.com/watch?v=GNU6Ue9HUJE")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(String.class)
        .isEqualTo(videoEmbedCode);
  }
}
