package com.nvrsk;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@FunctionalInterface
public interface ScenarioFactory<T extends Scenario> extends Iterable<T>{

    //@Nonnull
    @SafeVarargs
    static <T extends Scenario> ScenarioFactory<T> of (ScenarioFactory<T>... factories){
        return () -> Stream.of(factories)
                .map(ScenarioFactory::createScenarios)
                .flatMap(List::stream)
                .collect(toList());
    }

    //@Nonnull
    List<T> createScenarios();

    @Override
    //@Nonnull
    default Iterator<T> iterator(){
        return createScenarios().iterator();
    }
}
