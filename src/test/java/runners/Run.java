package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/wybranieTelefonuBezAbonamentu.feature",
        glue = {"stepdefinitions"}
)
public class Run extends AbstractTestNGCucumberTests {
}
