package org.zhuch.dnd35.entity.user;

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
                query = "SELECT user from User user where user.username = :username"
        )
})
public class User {

    public static final String FIND_BY_LOGIN = "User.findUserByUsername";

    @Id
    @NotNull
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @NotNull
    @Column(name="username", unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(name="password_hash", nullable = false)
    private String passwordHash;

    User(@NotNull String id, @NotNull String username, @NotNull String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public User() {
        this.id = "";
        this.username = "";
        this.passwordHash = "";
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @NotNull
    public AppUserDetails produceDTO() {
        return AppUserDetails.builder()
                .id(id)
                .username(username)
                .passwordHash(passwordHash)
                .build();
    }

    public static class UserBuilder {
        private @NotNull String id;
        private @NotNull String username;
        private @NotNull String passwordHash;

        UserBuilder() {
            this.id = "";
            this.username = "";
            this.passwordHash = "";
        }

        public UserBuilder id(@NotNull String id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(@NotNull String username) {
            this.username = username;
            return this;
        }

        public UserBuilder passwordHash(@NotNull String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public User build() {
            return new User(id, username, passwordHash);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", login=" + this.username + ", passwordHash=" + this.passwordHash + ")";
        }
    }
}
