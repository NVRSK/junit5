package com.nvrsk;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.commons.util.Preconditions;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScenarioTestExtension implements TestTemplateInvocationContextProvider, AfterTestExecutionCallback {

    private static void checkDuplicates(String testName, List<Scenario> scenarios) {
        Map<String, List<Scenario>> duplicates = scenarios.stream()
                .collect(Collectors.groupingBy(Scenario::getDescription))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (duplicates.size() != 0) {
            throw new PreconditionViolationException(
                    String.format("There are duplicates scenario names in %s (total %s): %s",
                            testName, duplicatesCount(duplicates), duplicatesToString(duplicates)));
        }
    }

    private static String duplicatesToString(Map<String, List<Scenario>> duplicates) {
        return duplicates.entrySet().stream()
                .map(entry -> String.format("[%s]: count %s", entry.getKey(), entry.getValue().size()))
                .collect(Collectors.joining(" ;"));
    }

    private static int duplicatesCount(Map<String, List<Scenario>> duplicates) {
        return duplicates.values()
                .stream()
                .mapToInt(List::size)
                .map(i -> i - 1)
                .sum();
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            //log.error("Test unique id to reproduce on local env: {}", context.getUniqueId())
            System.out.println(String.format("Test unique id to reproduce on local env: {%s}", context.getUniqueId()));
        }
    }

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        Constructor<?>[] constructors = context.getRequiredTestClass().getDeclaredConstructors();

        if (constructors.length != 1) {
            return false;
        }

        Constructor<?> constructor = constructors[0];
        Class<?>[] parameterTypes = constructor.getParameterTypes();

        long scenarioParameters = Stream.of(parameterTypes)
                .filter(Scenario.class::isAssignableFrom)
                .count();

        return scenarioParameters == 1;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        ScenarioTest annotation = context.getRequiredTestMethod().getDeclaredAnnotation(ScenarioTest.class);

        AtomicLong invocationCount = new AtomicLong(0);

        List<Scenario> scenarios = Stream.of(annotation.factory())
                .map(ConstructorUtil::executeOnlyDefaultConstructor)
                .map(ScenarioFactory::createScenarios)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        checkDuplicates(context.getDisplayName(), scenarios);

        return scenarios.stream()
                .map(scenario -> createInvocationContext(scenario))
                .peek(invocationContext -> invocationCount.incrementAndGet())
                .onClose(() -> Preconditions.condition(invocationCount.get() > 0,
                        "Configuration error: you must configure at least 1 non-empty ScenarioFactory for this @ ScenarioTest"));
    }

    private TestTemplateInvocationContext createInvocationContext(Scenario scenario) {
        return new ScenarioTestInvocationContext(scenario);
    }
}
