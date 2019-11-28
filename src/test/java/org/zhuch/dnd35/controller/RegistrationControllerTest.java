package org.zhuch.dnd35.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zhuch.dnd35.api.GetUserRq;
import org.zhuch.dnd35.api.RegistrationRs;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    private RegistrationController controller;

    @Test
    void contextLoads() {
    }

    @Test
    void simpleTest() {
        final GetUserRq getUserRq = new GetUserRq();
        getUserRq.setLogin("");
        final RegistrationRs registrationRs = controller.findByLogin(getUserRq);
        assertNotNull(registrationRs);
        assertNotNull(registrationRs.getSuccess());
        assertFalse(registrationRs.getSuccess());
    }
}
