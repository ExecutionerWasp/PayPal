package org.model.domain;

import lombok.*;
import org.model.annotation.AccessField;
import org.model.annotation.FieldType;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientPayPal implements Serializable {
    @AccessField(FieldType.ID)
    private String id;

    @AccessField(FieldType.TOKEN)
    private String accessToken;

    @AccessField(FieldType.SECRET)
    private String accessSecret;
}
