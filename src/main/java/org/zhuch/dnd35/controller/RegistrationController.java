package org.zhuch.dnd35.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.zhuch.dnd35.api.GetUserRq;
import org.zhuch.dnd35.api.RegistrationRq;
import org.zhuch.dnd35.api.RegistrationRs;
import org.zhuch.dnd35.entity.user.AppUserDetails;
import org.zhuch.dnd35.service.IUserService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationRs create(@RequestBody final RegistrationRq request) {
        final String username = Optional.ofNullable(request.getUsername()).orElse("");
        if (userService.existByLogin(username)) {
            return RegistrationRs.builder()
                    .message("Login already exists") //TODO: настроить ошибки по протоколу http
                    .success(false)
                    .build();
        }
        final AppUserDetails user = userService.save(AppUserDetails.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build());
        return RegistrationRs.builder()
                .id(user.getId())
                .username(user.getUsername())
                .success(true)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationRs findByLogin(@RequestBody final GetUserRq request) {
        final AppUserDetails user = userService.loadUserByUsername(
                Optional.ofNullable(request.getUsername()).orElse("")); //TODO: фильтр по входным параметрам?
        if (user == null) {
            return RegistrationRs.builder() //TODO: настроить ошибки по протоколу http
                    .message("No such user")
                    .success(false)
                    .build();
        }
        return RegistrationRs.builder()
                .id(user.getId())
                .username(user.getUsername())
                .success(true)
                .build();
    }
}
