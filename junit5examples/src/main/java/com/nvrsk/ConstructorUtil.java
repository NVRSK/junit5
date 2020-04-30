package com.nvrsk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ConstructorUtil {

    private ConstructorUtil() {
    }

    //@Nonnull
    public static <T> T executeOnlyDefaultConstructor(/*@Nonnull*/ Class<T> tClass) {
        try {
            if (tClass.getDeclaredConstructors().length != 1) {
                throw new IllegalArgumentException(String.format("Found more than 1 constructor in class %s. Should be only 1 zero-argument", tClass));
            }

            Constructor<? extends T> constructor;
            try {
                constructor = tClass.getDeclaredConstructor();
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException(String.format("Zero-argument constructor is not found in class %s", tClass));
            }

            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(String.format("Exception occurred trying to instance class %s", tClass), e);
        }
    }
}
