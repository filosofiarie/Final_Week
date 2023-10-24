package stepDef;

import config.env;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class checkout extends env {
    public checkout() {
    }

    @Before
    public void before() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions opt = new EdgeOptions();
        driver = new EdgeDriver(opt);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @After
    public void after(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File srcFile = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/" + scenario.getName() + ".png"));
        }

        driver.quit();
    }
}
