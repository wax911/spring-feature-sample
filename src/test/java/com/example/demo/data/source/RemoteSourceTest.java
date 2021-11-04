package com.example.demo.data.source;

import com.example.demo.data.model.SimilarContainer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@WireMockTest(httpPort = 8585)
class RemoteSourceTest {

    @Autowired
    private RemoteSource remoteSource;

    private String getSampleResponse() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("test-response.json");
        assertThat(inputStream).isNotNull();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream)
        );

        String contentBuffered = bufferedReader.lines()
                .collect(Collectors.joining("\n"));

        inputStream.close();
        bufferedReader.close();

        return contentBuffered;
    }

    @Test
    void test_response_body_conversion(WireMockRuntimeInfo wireMockRuntimeInfo) throws IOException, ExecutionException, InterruptedException {
        WireMock wireMock = wireMockRuntimeInfo.getWireMock();
        wireMock.register(
            WireMock.get("/similar")
                    .willReturn(
                        WireMock.okJson(
                            getSampleResponse()
                        )
                    )
        );

        CompletableFuture<SimilarContainer> completableResponse =
                remoteSource.getSimilar("test", 2, 1, null);
        SimilarContainer similarContainer = completableResponse.get();
        assertThat(similarContainer).isNotNull();
    }
}