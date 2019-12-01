package org.zhuch.dnd35.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "authResponse")
public class AuthResponse {
    @Nullable
    private String refresh;
    @Nullable
    private Boolean success;
    @Nullable
    private String message;
}
