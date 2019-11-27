package org.zhuch.dnd35.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zhuch.dnd35.entity.UserDTO;

public interface IUserService {
    @Nullable
    UserDTO getUserByLogin(@NotNull String login);

    @NotNull
    Boolean existByLogin(@NotNull String login);

    @NotNull
    UserDTO get(@NotNull String id);

    @NotNull
    UserDTO save(@NotNull UserDTO entityDTO);

    @NotNull
    Boolean remove(@NotNull String id);
}
