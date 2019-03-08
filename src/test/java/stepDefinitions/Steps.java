package stepDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageObjects.Exercise1Page;


public class Steps {
    WebDriver driver;
    Exercise1Page exercise1Page;

    @Given("^the site is opened with chrome$")
    public void the_site_is_opened_with_chrome(){
        String exePath = "/Users/zhaoqiwang/Downloads/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.exercise1.com/values");
        exercise1Page = new Exercise1Page(driver);
    }

    @When("^verify the total number of value$")
    public void verify_the_total_number_of_value() {
        assert(exercise1Page.checkNumberOfValues());
    }

    @When("^verify all value are greater than zero$")
    public void verify_all_value_are_greater_than_zero(){
        assert(exercise1Page.areValuesGreaterThanzero());

    }

    @When("^verify values are formatted as currencies$")
    public void verify_values_are_formatted_as_currencies() {
        assert(exercise1Page.areValueCurrencies());

    }

    @When("^verify total value equals to sum of values$")
    public void verify_total_value_equals_to_sum_of_values(){
        assert (exercise1Page.checkTotalBlance());
        driver.close();

    }



}
