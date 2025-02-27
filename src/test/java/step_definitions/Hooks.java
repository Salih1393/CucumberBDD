package step_definitions;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import util.Driver;

public class Hooks {
    public static Scenario currentScenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        Driver.getDriver();
        currentScenario = scenario;
    }

    @After
    public void afterScenario() {
        Driver.quitDriver();
    }

    @AfterStep
    public void afterStepActions(){
        //CucumberUtils.logInfo("", true);
    }
}
