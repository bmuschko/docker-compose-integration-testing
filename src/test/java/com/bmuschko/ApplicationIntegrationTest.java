package com.bmuschko;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationIntegrationTest {
    @ParameterizedTest(name = "can resolve application URL {0} times and receive correct count")
    @ValueSource(ints = { 1, 2, 3 })
    void canResolveApplicationUrl(int times) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:5000/"))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandler.asString());
        assertTrue(response.body().contains(String.format("Welcome to this awesome page! You've visited me %s times.", times)));
    }
}