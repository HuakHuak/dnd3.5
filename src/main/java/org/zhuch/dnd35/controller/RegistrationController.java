package org.zhuch.dnd35.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhuch.dnd35.api.GetUserRq;
import org.zhuch.dnd35.api.RegistrationRq;
import org.zhuch.dnd35.api.RegistrationRs;
import org.zhuch.dnd35.entity.UserDTO;
import org.zhuch.dnd35.service.IUserService;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final IUserService userService;

    @Autowired
    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationRs create(@RequestBody final RegistrationRq request) {
        final String login = Optional.ofNullable(request.getLogin()).orElse("");
        if (userService.existByLogin(login)) {
            return RegistrationRs.builder()
                    .message("Login already exists") //TODO: настроить ошибки по протоколу http
                    .success(false)
                    .build();
        }
        final UserDTO user = userService.save(UserDTO.builder()
                .login(request.getLogin())
                .passwordHash(request.getPassword())
                .build());
        return RegistrationRs.builder()
                .id(user.getId())
                .login(user.getLogin())
                .success(true)
                .build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RegistrationRs findByLogin(@RequestBody final GetUserRq request) {
        final UserDTO user = userService.getUserByLogin(
                Optional.ofNullable(request.getLogin()).orElse("")); //TODO: фильтр по входным параметрам?
        if (user == null) {
            return RegistrationRs.builder() //TODO: настроить ошибки по протоколу http
                    .message("No such user")
                    .success(false)
                    .build();
        }
        return RegistrationRs.builder()
                .id(user.getId())
                .login(user.getLogin())
                .success(true)
                .build();
    }
}
