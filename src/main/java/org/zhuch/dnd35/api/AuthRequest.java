package org.zhuch.dnd35.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "authRequest")
public class AuthRequest {
    @Nullable
    private String username;
    @Nullable
    private String password;
}
