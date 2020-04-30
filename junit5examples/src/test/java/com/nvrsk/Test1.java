package com.nvrsk;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test1 {
    private final int a;
    private final int b;
    private final int result;

    public Test1(Data data) {
        this.a = data.a;
        this.b = data.b;
        this.result = data.result;
    }

    static class Data extends BaseScenario {
        private final int a;
        private final int b;
        private final int result;

        public Data(int a, int b, int result) {
            super(String.format("a = %s, b = %s: a+b= %s", a, b, result));
            this.a = a;
            this.b = b;
            this.result = result;
        }
    }

    static class Factory implements ScenarioFactory<Data>{

        @Override
        public List<Data> createScenarios() {
            return asList(
                    new Data(1,2,3),
                    new Data(-1, 1, 10)
            );
        }
    }

    @ScenarioTest(factory = Factory.class)
    void test(){
        assertEquals(result, a+b);
    }
}
