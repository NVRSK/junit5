package com.nvrsk;

public interface Scenario {

    //@Nonnull
    String getDescription();

    interface Builder<T extends Scenario>{

        T build();
    }
}
