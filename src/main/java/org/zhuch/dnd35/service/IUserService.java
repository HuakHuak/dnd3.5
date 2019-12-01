package org.zhuch.dnd35.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.zhuch.dnd35.entity.user.AppUserDetails;

public interface IUserService extends UserDetailsService {
    @Nullable
    AppUserDetails loadUserByUsername(@NotNull String username);

    @NotNull
    Boolean existByLogin(@NotNull String login);

    @NotNull
    AppUserDetails get(@NotNull String id);

    @NotNull
    AppUserDetails save(@NotNull AppUserDetails entityDTO);

    @NotNull
    Boolean remove(@NotNull String id);
}
