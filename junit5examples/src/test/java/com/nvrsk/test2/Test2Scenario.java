package com.nvrsk.test2;

import com.nvrsk.BaseScenario;
import com.nvrsk.Scenario;
import com.nvrsk.steps.StepScenarioBuilder;

public class Test2Scenario extends BaseScenario {

    private int a;
    private int b;
    private int result;

    public Test2Scenario(int a, int b, int result) {
        super(String.format("a = %s, b = %s: a+b= %s", a, b, result));
        this.a = a;
        this.b = b;
        this.result = result;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getResult() {
        return result;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setResult(int result) {
        this.result = result;
    }

    static class Builder implements Scenario.Builder<Test2Scenario>{

        private int a;
        private int b;
        private int result;

        public void withA(int a) {
            this.a = a;
        }

        public void withB(int b) {
            this.b = b;
        }

        public void withResult(int result) {
            this.result = result;
        }

        public static StepScenarioBuilder<Test2Scenario, Builder> scenario(){
            return StepScenarioBuilder.scenario(new Builder());
        }

        @Override
        public Test2Scenario build() {
            return new Test2Scenario(a, b, result);
        }
    }
}
