package org.reflect;

import lombok.NonNull;
import org.model.annotation.AccessField;
import org.exception.AccessRequiredFieldsException;
import org.model.annotation.FieldType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FieldsResolver {
    private Map<FieldType, Field>
            clientRequiredFields;
    private Class<?> clientClass;

    public FieldsResolver(@NonNull Object client) throws AccessRequiredFieldsException {
        this.clientClass = client.getClass();

        clientRequiredFields = Stream.of(clientClass.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(AccessField.class))
                .collect(Collectors.toMap(key -> {
                            key.setAccessible(true);
                            return key.getAnnotation(AccessField.class).value();
                        },
                        value -> (Field)value));

        if (clientRequiredFields.size() < FieldType.values().length) {
            List<Object> missingFields = Stream.of(FieldType.values())
                    .filter(field -> !clientRequiredFields.containsKey(field))
                    .map(field -> field.name())
                    .collect(Collectors.toList());
            throw new AccessRequiredFieldsException
                    ("Client required clientRequiredFields:" + missingFields);
        }
    }

    public Object getFieldsValue(
            @NonNull FieldType fieldType,
            @NonNull Object instance
    )
    {
        assert(instance.getClass() == clientClass);
        try {
            instance = clientRequiredFields.get(fieldType).get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public List<Object> getFieldsValues(@NonNull Object instance){
        return Stream.of(FieldType.values())
                .map(type -> getFieldsValue(type, instance))
                .collect(Collectors.toList());
    }
}
