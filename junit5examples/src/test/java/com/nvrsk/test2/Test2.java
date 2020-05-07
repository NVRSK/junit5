package com.nvrsk.test2;

import com.nvrsk.ScenarioTest;
import com.nvrsk.tests.BaseScenarioTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 extends BaseScenarioTest<Test2Scenario>{

    public Test2(Test2Scenario scenario) {
        super(scenario);
    }

    @ScenarioTest(factory = Test2ScenarioFactory.class)
    void test(){
        assertEquals(scenario.getResult(), scenario.getA() + scenario.getB());
    }
}
