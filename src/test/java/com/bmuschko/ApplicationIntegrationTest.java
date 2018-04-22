package com.bmuschko;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationIntegrationTest {
    private static HttpClient httpClient;
    private static HttpRequest request;

    @BeforeAll
    static void setup() {
        httpClient = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5000/"))
            .GET()
            .build();
    }

    @Test
    void canResolveApplicationUrl() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandler.asString());
        assertTrue(response.body().contains("Welcome to this awesome page! You've visited me 1 times."));

        response = httpClient.send(request, HttpResponse.BodyHandler.asString());
        assertTrue(response.body().contains("Welcome to this awesome page! You've visited me 2 times."));
    }
}