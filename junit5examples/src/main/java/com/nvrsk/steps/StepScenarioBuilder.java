package com.nvrsk.steps;

import com.nvrsk.Scenario;

public class StepScenarioBuilder<S extends Scenario, B extends Scenario.Builder<S>> {

    private final B builder;


    public StepScenarioBuilder(B builder) {
        this.builder = builder;
    }

    public WhenScenarioBuilder<S, B> given(Given<? super B> given){
        given.buildTo(builder);
        return new WhenScenarioBuilder<>(builder);
    }

    public ThenScenarioBuilder<S, B> when(When<? super B> when){
        when.buildTo(builder);
        return new ThenScenarioBuilder<>(builder);
    }

    public static <T extends Scenario, B extends Scenario.Builder<T>> StepScenarioBuilder<T, B> scenario(B builder){
        return new StepScenarioBuilder<>(builder);
    }

    public static class WhenScenarioBuilder<S extends Scenario, B extends Scenario.Builder<S>> implements Scenario.Builder<S>{

        private final B builder;

        public WhenScenarioBuilder(B builder) {
            this.builder = builder;
        }

        public ThenScenarioBuilder<S, B> when(When<? super B> when){
            when.buildTo(builder);
            return new ThenScenarioBuilder<>(builder);
        }

        @Override
        public S build(){
            return builder.build();
        }

        public B toBuilder(){
            return builder;
        }
    }

    public static class ThenScenarioBuilder<S extends Scenario, B extends Scenario.Builder<S>> {

        private final B builder;

        public ThenScenarioBuilder(B builder) {
            this.builder = builder;
        }

        public WhenScenarioBuilder<S, B> then(Then<? super B> then){
            then.buildTo(builder);
            return new WhenScenarioBuilder<>(builder);
        }
    }
}
