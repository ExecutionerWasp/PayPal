package org.model.domain;

import lombok.*;
import org.model.annotation.AccessField;
import org.model.annotation.FieldType;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPayPal {
    @AccessField(FieldType.ID)
    @NonNull private String id;

    @AccessField(FieldType.TOKEN)
    @NonNull private String accessToken;

    @AccessField(FieldType.SECRET)
    @NonNull private String accessSecret;
}
