package org.zhuch.dnd35.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhuch.dnd35.entity.User;
import org.zhuch.dnd35.entity.UserDTO;
import org.zhuch.dnd35.repository.IUserRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public @Nullable UserDTO getUserByLogin(@NotNull final String login) {
        final User user = userRepository.findUserByLogin(login);
        return user == null ? null : user.produceDTO();
    }

    @Override
    @Transactional
    public @NotNull Boolean existByLogin(@NotNull String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public @NotNull UserDTO get(@NotNull final String id) {
        return UserDTO.builder().build();
    }


    @Override
    @Transactional
    public @NotNull UserDTO save(@NotNull final UserDTO entityDTO) {
        final User user = userRepository.save(
                User.builder()
                        .id(Optional.of(entityDTO.getId())
                                .filter(id -> !id.isEmpty())
                                .orElse(UUID.randomUUID().toString()))
                        .login(entityDTO.getLogin())
                        .passwordHash(entityDTO.getPasswordHash())
                        .build());
        return user.produceDTO();
    }

    @Override
    @Transactional
    public @NotNull Boolean remove(@NotNull final String id) {
        return false;
    }
}
