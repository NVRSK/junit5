package com.nvrsk.steps;

@FunctionalInterface
public interface Then<T> {

    void buildTo(T builder);

    default Then<T> and(Then<? super T> nextStep){
        return builder -> {
            buildTo(builder);
            nextStep.buildTo(builder);
        };
    }
}
