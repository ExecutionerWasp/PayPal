package org.reflect;

import lombok.NonNull;
import org.exception.MetaDateAnnotationNotFoundException;
import org.model.annotation.MetaDate;

public final class MetaDateResolver {
    private Class<?> annotatedClass;

    public MetaDateResolver(@NonNull Object object) throws MetaDateAnnotationNotFoundException {
        if (!annotatedClass.isAnnotationPresent(MetaDate.class))
            throw new MetaDateAnnotationNotFoundException(
                    "Class:" +
                            annotatedClass.getSimpleName()
                            + "is not annotated");
        else
            this.annotatedClass = object.getClass();
    }

    public String getMethod() {
        return annotatedClass.getAnnotation(MetaDate.class).method();
    }

    public String getIntent(){
        return annotatedClass.getAnnotation(MetaDate.class).intent();
    }
}
