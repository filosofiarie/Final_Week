package stepDef;

public class logout {

import config.env;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

    public class logout extends env {
        public int duration = 10;
        WebDriverWait wait;


        public logout() {
            this.wait = new WebDriverWait(driver, (long)this.duration);
            this.elementLogin = new pageLogout();
        }

        @Given("user is on SauceDemo homepage")
        public void user_is_on_sauce_demo_homepage() {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.elementLogin.getBtn_login()));
        }

        @When("user input (.*) as username$")
        public void user_input_username(String username) {
            WebElement field_userName = driver.findElement(this.elementLogin.getField_username());
            field_userName.isDisplayed();
            field_userName.sendKeys(new CharSequence[]{username});
        }

        @And("user input (.*) as password$")
        public void user_input_password(String password) {
            WebElement field_password = driver.findElement(this.elementLogin.getField_password());
            Assert.assertTrue(field_password.isEnabled());
            field_password.sendKeys(new CharSequence[]{password});
        }

        @And("user click enter")
        public void user_click_enter() {
            WebElement field_password = driver.findElement(this.elementLogin.getField_password());
            field_password.sendKeys(new CharSequence[]{Keys.ENTER});
        }

        @Then("user verify (.*) login result$")
        public void user_verify_success_login_result(String status) {
            if (status.contains("success")) {
                this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header_secondary_container']/span[@class='title']")));
            } else {
                driver.findElement(By.className("error-button"));
            }

        }

        @Then("user verify failed login")
        public void user_verify_failed_login() {
            driver.findElement(By.className("error-button"));
        }
    }
}
