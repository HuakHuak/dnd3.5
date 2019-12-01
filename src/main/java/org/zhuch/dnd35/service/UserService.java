package org.zhuch.dnd35.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zhuch.dnd35.entity.user.User;
import org.zhuch.dnd35.entity.user.AppUserDetails;
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
    public @Nullable AppUserDetails loadUserByUsername(@NotNull final String username) {
        final User user = userRepository.findUserByUsername(username);
        return user == null ? null : user.produceDTO();
    }

    @Override
    @Transactional
    public @NotNull Boolean existByLogin(@NotNull String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public @NotNull AppUserDetails get(@NotNull final String id) {
        return AppUserDetails.builder().build();
    }


    @Override
    @Transactional
    public @NotNull AppUserDetails save(@NotNull final AppUserDetails entityDTO) {
        final User user = userRepository.save(
                User.builder()
                        .id(Optional.of(entityDTO.getId())
                                .filter(id -> !id.isEmpty())
                                .orElse(UUID.randomUUID().toString()))
                        .username(entityDTO.getUsername())
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
