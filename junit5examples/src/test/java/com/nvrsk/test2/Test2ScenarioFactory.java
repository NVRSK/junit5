package com.nvrsk.test2;

import com.nvrsk.Scenario;
import com.nvrsk.ScenarioFactory;
import com.nvrsk.steps.Then;
import com.nvrsk.steps.When;

import java.util.List;
import java.util.stream.Collectors;

import static com.nvrsk.test2.Test2Scenario.Builder.scenario;
import static java.util.Arrays.asList;

public class Test2ScenarioFactory implements ScenarioFactory<Test2Scenario> {

    @Override
    public List<Test2Scenario> createScenarios() {
        return prepareScenarios().stream()
                .map(Scenario.Builder::build)
                .collect(Collectors.toList());
    }

        private List<Scenario.Builder<Test2Scenario>> prepareScenarios() {
            return asList(
                    scenario()
                            .when(aIs(1).and(bIs(2)))
                            .then(resultIs(3)),
                    scenario()
                            .when(aIs(-1).and(bIs(-2)))
                            .then(resultIs(-3)),
                    scenario()
                            .when(aIs(1).and(bIs(0)))
                            .then(resultIs(1)),
                    scenario()
                            .when(aIs(-1).and(bIs(0)))
                            .then(resultIs(-1)),
                    scenario()
                            .when(aIs(0).and(bIs(0)))
                            .then(resultIs(0)),
                    scenario()
                            .when(aIs(-10).and(bIs(2)))
                            .then(resultIs(-8))
            );
        }

    private static When<Test2Scenario.Builder> aIs(int a){
        return builder -> builder.withA(a);
    }

    private static When<Test2Scenario.Builder> bIs(int b){
        return builder -> builder.withB(b);
    }

    private static Then<Test2Scenario.Builder> resultIs(int result){
        return builder -> builder.withResult(result);
    }
}
