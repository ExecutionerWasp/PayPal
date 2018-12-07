package org.model;

import lombok.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Resolvers {
    public static List<Field> getAnnotatedFields(
            @NonNull Class<?> clazz,
            @NonNull Class<? extends Annotation> annotation) {
        return Stream.of(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toList());
    }

    public static List<Object> getAnnotatedFieldsValues(
            @NonNull Object instance,
            @NonNull Class<? extends Annotation> annotation){
        return Stream.of(instance.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(annotation))
                .peek(field -> field.setAccessible(true))
                .map(field -> {
                    try {
                        return field.get(instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
