package com.nvrsk.tests;

import com.nvrsk.Scenario;

public abstract class BaseScenarioTest<T extends Scenario> {

    protected final T scenario;

    public BaseScenarioTest( /*@Nonnull*/ T scenario) {
        this.scenario = scenario;
    }
}
