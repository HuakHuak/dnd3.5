package org.zhuch.dnd35.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

@Getter
public class AppUserDetails implements UserDetails {

    @NotNull
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String passwordHash;

    AppUserDetails(@Nullable String id, @Nullable String username, @Nullable String passwordHash) {
        this.id = Optional.ofNullable(id).orElse("");
        this.username = Optional.ofNullable(username).orElse("");
        this.passwordHash = Optional.ofNullable(passwordHash).orElse("");
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @NoArgsConstructor
    public static class Builder {
        private @Nullable String id;
        private @Nullable String username;
        private @Nullable String passwordHash;

        public Builder id(@Nullable String id) {
            this.id = id;
            return this;
        }

        public Builder username(@Nullable String username) {
            this.username = username;
            return this;
        }

        public Builder passwordHash(@Nullable String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public AppUserDetails build() {
            return new AppUserDetails(id, username, passwordHash);
        }

        public String toString() {
            return "UserDTO.UserDTOBuilder(id=" + this.id + ", login=" + this.username + ", passwordHash=" + this.passwordHash + ")";
        }
    }
}
