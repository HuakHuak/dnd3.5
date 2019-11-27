package org.zhuch.dnd35.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class RqTests {

    @Test
    public void toJsonTest() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final RegistrationRq userRq = new RegistrationRq();
        userRq.setLogin("login");
        userRq.setPassword("pass");
        System.out.println(mapper.writeValueAsString(userRq));
    }
}
