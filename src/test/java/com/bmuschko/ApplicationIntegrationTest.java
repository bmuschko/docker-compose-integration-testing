package com.bmuschko;

import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URI;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import jdk.incubator.http.HttpResponse.BodyHandler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationIntegrationTest {
    @Test
    void canResolveApplicationUrl() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:5000/"))
            .GET()
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandler.asString());
        String responseBody = response.body();
        assertTrue(responseBody.startsWith("Welcome to this awesome page!"));
    }
}