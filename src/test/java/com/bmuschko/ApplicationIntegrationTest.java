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
	private static final String WEB_SERVICE_HOST = System.getProperty("web.host");
	private static final Integer WEB_SERVICE_PORT = Integer.getInteger("web.tcp.5000");
	private static final String WEB_SERVICE_URI = "http://" + WEB_SERVICE_HOST + ":" + WEB_SERVICE_PORT + "/";

    @ParameterizedTest(name = "can resolve application URL {0} times and receive correct count")
    @ValueSource(ints = { 1, 2, 3 })
    void canResolveApplicationUrl(int times) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(WEB_SERVICE_URI))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandler.asString());
        assertTrue(response.body().contains(String.format("Welcome to this awesome page! You've visited me %s times.", times)));
    }
}