package org.zhuch.dnd35.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "registrationRq")
public class RegistrationRq implements Serializable {
    @Nullable
    private String login;
    @Nullable
    private String password;
}
