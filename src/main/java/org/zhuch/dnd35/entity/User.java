package org.zhuch.dnd35.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "app_user")
@NamedQueries(value = {
        @NamedQuery(
                name = User.FIND_BY_LOGIN,
                query = "SELECT user from User user where user.login = :login"
        )
})
public class User {

    public static final String FIND_BY_LOGIN = "User.findUserByLogin";

    @Id
    @NotNull
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @NotNull
    @Column(name="login", unique = true, nullable = false)
    private String login;

    @NotNull
    @Column(name="password_hash", nullable = false)
    private String passwordHash;

    User(@NotNull String id, @NotNull String login, @NotNull String passwordHash) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public User() {
        this.id = "";
        this.login = "";
        this.passwordHash = "";
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @NotNull
    public UserDTO produceDTO() {
        return UserDTO.builder()
                .id(id)
                .login(login)
                .passwordHash(passwordHash)
                .build();
    }

    public static class UserBuilder {
        private @NotNull String id;
        private @NotNull String login;
        private @NotNull String passwordHash;

        UserBuilder() {
            this.id = "";
            this.login = "";
            this.passwordHash = "";
        }

        public UserBuilder id(@NotNull String id) {
            this.id = id;
            return this;
        }

        public UserBuilder login(@NotNull String login) {
            this.login = login;
            return this;
        }

        public UserBuilder passwordHash(@NotNull String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public User build() {
            return new User(id, login, passwordHash);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", login=" + this.login + ", passwordHash=" + this.passwordHash + ")";
        }
    }
}
