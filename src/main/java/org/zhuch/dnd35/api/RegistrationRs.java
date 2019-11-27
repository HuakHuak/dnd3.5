package org.zhuch.dnd35.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "userRs")
public class RegistrationRs implements Serializable {
    @Nullable
    private String id;
    @Nullable
    private String login;
    @Nullable
    private Boolean success;
    @Nullable
    private String message;
}
