package org.reflect;

import org.model.annotation.AccessField;
import org.exception.AccessRequiredFieldsException;
import org.model.annotation.FieldType;

import java.beans.ConstructorProperties;
import java.lang.reflect.Field;
import java.util.*;

public final class FieldsResolver {

    private final Map<FieldType, Field>
            clientRequiredFields = new HashMap<>();
    private Object client;

    @ConstructorProperties("pay-pal-client")
    public FieldsResolver(Object client) throws AccessRequiredFieldsException {
        Objects.requireNonNull(client);
        this.client = client;
        for (Field f :
                client.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(AccessField.class)){
                if (!clientRequiredFields.containsKey(f.getAnnotation(AccessField.class).value())){
                    f.setAccessible(true);
                    clientRequiredFields.put(f.getAnnotation(AccessField.class).value(), f);
                } else {
                    throw new AccessRequiredFieldsException(
                            "Client required field:" +
                                    f.getAnnotation(AccessField.class).value() +
                                    "is exists");
                }
            }
        }
        if (clientRequiredFields.size() < FieldType.values().length) {
            List<String> missingFields = new ArrayList<>();
            for (FieldType f :
                    FieldType.values()) {
                if (!clientRequiredFields.containsKey(f)) {
                    missingFields.add(f.name());
                }
            }
            throw new AccessRequiredFieldsException
                    ("Client required clientRequiredFields:" + missingFields);
        }
    }

    public Object getFieldObject(FieldType fieldType) throws IllegalAccessException {
        Objects.requireNonNull(client);
        return clientRequiredFields.get(fieldType).get(client);
    }
}
