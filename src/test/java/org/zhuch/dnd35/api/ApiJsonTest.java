package org.zhuch.dnd35.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiJsonTest {

    @Test
    void testAuthRequest() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testLogin");
        authRequest.setPassword("testPassword");
        assertEquals("{\n" +
                "  \"login\" : \"testLogin\",\n" +
                "  \"password\" : \"testPassword\"\n" +
                "}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authRequest));
    }


    @Test
    void testAuthResponse() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final AuthResponse authResponse = new AuthResponse();
        authResponse.setRefresh("testToken");
        authResponse.setSuccess(true);
        authResponse.setMessage("testMsg");
        assertEquals("{\n" +
                "  \"token\" : \"testToken\",\n" +
                "  \"success\" : true,\n" +
                "  \"message\" : \"testMsg\"\n" +
                "}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(authResponse));
    }
}
