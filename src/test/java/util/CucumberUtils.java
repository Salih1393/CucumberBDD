package util;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import step_definitions.Hooks;

public class CucumberUtils {
    public static void logInfo(String msg, boolean takeScreenshot) {
        Scenario scenario = Hooks.currentScenario;
        scenario.log(DateUtils.currentDateTime() + " INFO: " + msg);

        if (takeScreenshot) {
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
}
