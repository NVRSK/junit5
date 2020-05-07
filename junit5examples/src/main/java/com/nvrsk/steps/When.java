package com.nvrsk.steps;

@FunctionalInterface
public interface When<T> {

    void buildTo(T builder);

    default When<T> and(When<? super T> nextStep){
        return builder -> {
            buildTo(builder);
            nextStep.buildTo(builder);
        };
    }
}
