package com.contact.geo.contactgeo.client;

import net.minidev.json.JSONValue;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class AbstractClientTest {

    public static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    protected String getJsonFileAsString(final String path) {
        final InputStream resourceAsStream;
        resourceAsStream = this.getClass().getResourceAsStream(String.format("/%s", path));
        return JSONValue.parse(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8)).toString();
    }

}
