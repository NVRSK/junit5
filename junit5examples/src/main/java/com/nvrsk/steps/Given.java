package com.nvrsk.steps;

@FunctionalInterface
public interface Given<T> {

    void buildTo(T builder);

    default  Given<T> and(Given<? super T> nextStep){
        return builder -> {
            buildTo(builder);
            nextStep.buildTo(builder);
        };
    }
}
