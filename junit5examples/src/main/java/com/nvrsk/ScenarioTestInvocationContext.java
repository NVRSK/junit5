package com.nvrsk;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import java.util.Collections;
import java.util.List;

public class ScenarioTestInvocationContext implements TestTemplateInvocationContext{

    private final Scenario scenario;

    public ScenarioTestInvocationContext(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return "[" + scenario.getDescription() + "]";
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return Collections.singletonList(new ScenarioParameterResolver(scenario));
    }
}
