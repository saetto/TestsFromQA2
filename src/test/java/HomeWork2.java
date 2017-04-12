import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * This class is used for QA2 lesions
 */
public class HomeWork2 {


    private static final Logger LOGGER = Logger.getLogger(HomeWork2.class);

    /**
     * This test will test SportsDirect.com filter
     */
    @Test
    public void hwTest() {

        LOGGER.info("hey hey");
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://www.sportsdirect.com");

        Boolean modalCloseBtn = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("advertPopup")).isDisplayed();
            }
        });

        driver.findElement(By.xpath("(//button[@data-dismiss='modal'])[2]")).click();

//        driver.navigate().refresh();

        Boolean someNewCheckStart = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("(//*[contains(@class, 'sportsMenu')])[2]")).isDisplayed();
            }
        });



//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        driver.findElement(By.xpath("(//*[contains(@class, 'sportsMenu')])[2]")).click();

        WebElement someNewCheckStarttt = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("(//*[contains(text(), 'Swimming')])[2]"));
            }
        });

        someNewCheckStarttt.click();

        Boolean someNewCheck = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("(//*[contains(text(), 'swimsuits')])[3]")).isDisplayed();
            }
        });

        driver.findElement(By.xpath("(//*[contains(text(), 'swimsuits')])[2]")).click();

        driver.findElement(By.xpath("//*[contains(@data-filtername, 'Unisex Adults')]")).click();
        driver.findElement(By.xpath("//*[contains(@data-filtername, 'Girls')]")).click();

        List<WebElement> checkedItems = driver.findElements(By.xpath("//*[contains(@area-checked, 'true')]/span[2]"));

        Integer count = 0;

        for(WebElement element : checkedItems) {
            count = count + Integer.parseInt(element.getText());
        }

        Integer catalogElementCount = 0;

        catalogElementCount = driver.findElements(By.xpath("//*[@id='navli']/li")).size();

        Assert.assertEquals(count, catalogElementCount);

        driver.quit();
    }
}
