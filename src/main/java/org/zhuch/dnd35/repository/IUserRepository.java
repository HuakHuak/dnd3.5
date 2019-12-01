package org.zhuch.dnd35.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zhuch.dnd35.entity.user.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    @Nullable
    User findUserByUsername(@NotNull @Param("username") final String login);

    @NotNull
    Boolean existsByUsername(@NotNull final String login);
}
