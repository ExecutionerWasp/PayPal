package org.reflect;

import lombok.NonNull;
import org.model.annotation.AccessField;
import org.exception.AccessRequiredFieldsException;
import org.model.annotation.FieldType;

import java.lang.reflect.Field;
import java.util.*;

public final class FieldsResolver {
    private final Map<FieldType, Field>
            clientRequiredFields = new HashMap<>();
    private Class<?> clientClass;

    public FieldsResolver(@NonNull Object clientClass) throws AccessRequiredFieldsException {
        this.clientClass = clientClass.getClass();
        for (Field f :
                clientClass.getClass().getDeclaredFields()) {
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

    public Object getFieldsValue(
            @NonNull FieldType fieldType,
            @NonNull Object instance
    ) throws IllegalAccessException
    {
        assert(instance.getClass() == clientClass);
        return clientRequiredFields.get(fieldType).get(instance);
    }

    public List<Object> getFieldsValues(@NonNull Object instance){
        List<Object> values = new ArrayList<>();
            Arrays.asList(FieldType.values()).forEach(
                    fieldType -> {
                        try {
                            values.add(getFieldsValue(fieldType, instance));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        return values;
    }
}
