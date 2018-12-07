package org.model;

import lombok.NonNull;
import org.model.annotation.AccessField;
import org.model.exception.ClientResolverException;
import org.model.annotation.FieldType;
import org.model.annotation.PayPalClient;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ClientResolver {
    private static ClientResolver resolver;
    private Map<FieldType, Field> clientRequiredFields;
    private Class<?> client;

    private ClientResolver(){

    }

    public static ClientResolver getInstance(){
        if (resolver == null){
            synchronized (ClientResolver.class){
                if (resolver == null){
                    resolver = new ClientResolver();
                }
            }
        }
        return resolver;
    }

    public void initClientClass(@NonNull Class<?> client)
            throws ClientResolverException {
        this.client = client;
        if (!client.isAnnotationPresent(PayPalClient.class)){
            throw new ClientResolverException(
                    "Class:" +
                            this.client.getSimpleName()
                            + "is not annotated");
        }

        clientRequiredFields = new HashMap<>();
        Resolvers.getAnnotatedFields(this.client, AccessField.class)
                .forEach(field -> clientRequiredFields
                        .put(field.getAnnotation(AccessField.class).value(), field));
        if (clientRequiredFields.size() < FieldType.values().length) {
            List<Object> missingFields = Stream.of(FieldType.values())
                    .filter(field -> !clientRequiredFields.containsKey(field))
                    .map(Enum::name)
                    .collect(Collectors.toList());

            throw new ClientResolverException
                    ("ClientFieldsAnnotation:" + missingFields);
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
        return Resolvers.getAnnotatedFieldsValues(instance, AccessField.class);
    }

    public String getPaymentMethod(){
        Objects.requireNonNull(client);
        return client.getAnnotation(PayPalClient.class).method();
    }

    public String[] getPayerIntent(){
        Objects.requireNonNull(client);
        return client.getAnnotation(PayPalClient.class).intent();
    }
}
