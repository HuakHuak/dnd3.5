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
@XmlRootElement(name = "findUser")
public class GetUserRq implements Serializable {
    @Nullable
    private String username;
}
