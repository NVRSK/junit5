package com.nvrsk;

public abstract class BaseScenario implements Scenario{

    private final String description;

    protected BaseScenario(String description) {
        this.description = description;
    }

    //@Nonnull
    @Override
    public String getDescription() {
        return description;
    }
}
