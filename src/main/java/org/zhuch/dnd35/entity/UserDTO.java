package org.zhuch.dnd35.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@Getter
public class UserDTO {

    @NotNull
    private String id;

    @NotNull
    private String login;

    @NotNull
    private String passwordHash;

    UserDTO(@Nullable String id, @Nullable String login, @Nullable String passwordHash) {
        this.id = Optional.ofNullable(id).orElse("");
        this.login = Optional.ofNullable(login).orElse("");
        this.passwordHash = Optional.ofNullable(passwordHash).orElse("");
    }

    public static Builder builder() {
        return new Builder();
    }

    @NoArgsConstructor
    public static class Builder {
        private @Nullable String id;
        private @Nullable String login;
        private @Nullable String passwordHash;

        public Builder id(@Nullable String id) {
            this.id = id;
            return this;
        }

        public Builder login(@Nullable String login) {
            this.login = login;
            return this;
        }

        public Builder passwordHash(@Nullable String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(id, login, passwordHash);
        }

        public String toString() {
            return "UserDTO.UserDTOBuilder(id=" + this.id + ", login=" + this.login + ", passwordHash=" + this.passwordHash + ")";
        }
    }
}
