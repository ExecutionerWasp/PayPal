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
    private final String EXCEPTION_MISSING_MASSAGE
            = "Client required clientRequiredFields:";

    public FieldsResolver(@NonNull Object client)
            throws AccessRequiredFieldsException {
        clientRequiredFields = new HashMap<>();
        Parser.getAnnotatedFields(client.getClass(), AccessField.class)
                .forEach(field -> clientRequiredFields
                        .put(field.getAnnotation(AccessField.class).value(), field));
        if (clientRequiredFields.size() < FieldType.values().length) {
            List<Object> missingFields = Stream.of(FieldType.values())
                    .filter(field -> !clientRequiredFields.containsKey(field))
                    .map(Enum::name)
                    .collect(Collectors.toList());
            throw new AccessRequiredFieldsException
                    (EXCEPTION_MISSING_MASSAGE + missingFields);
        }
    }

    public Object getFieldValue(
            @NonNull FieldType fieldType,
            @NonNull Object instance){
        Objects.requireNonNull(clientRequiredFields);
        try {
            instance = clientRequiredFields.get(fieldType).get(instance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public List<Object> getFieldsValues(@NonNull Object instance){
        return Parser.getAnnotatedFieldsValues(instance, AccessField.class);
    }
}
