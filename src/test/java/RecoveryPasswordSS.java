import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by Andrei Pazniak on 4/23/2017.
 */
public class RecoveryPasswordSS {

    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    @Test
    public void recoveryPassword() throws InterruptedException {

        LOGGER.info("We are starting our test");
        WebDriver driver = getDriver();

        LOGGER.info("We are opening ss.lv recovery password page");
        driver.get("https://www.ss.lv/ru/pass-recovery/");

        LOGGER.info("Input e-mail");
        WebElement emailInput=driver.findElement(By.id("intxt"));
        Actions builder = new Actions(driver);
        Actions seriesOfActions = builder.moveToElement(emailInput).click().sendKeys(emailInput, "str_mary");
        seriesOfActions.perform();

        LOGGER.info("Press button Продолжить");
        driver.findElement(By.cssSelector("input[class='b inbtn']")).click();

        //LOGGER.info("We are going to the email");
        //WebDriver driver2 = getDriver();
        //driver2.get("https://mail.ru/");
        //WebElement email=driver2.findElement(By.id("mailbox__login"));
        //Actions builder2 = new Actions(driver2);
        //Actions seriesOfActions2 = builder2.moveToElement(email).click().sendKeys(email, "str_mary");
        //seriesOfActions2.perform();
        //WebElement password = driver2.findElement(By.id("mailbox__password"));
        //Actions seriesOfAction = builder2.moveToElement(password).click().sendKeys(password, "!2345qwert");
        //seriesOfAction.perform();
        //driver2.findElement(By.id("mailbox__auth__button")).click();

        LOGGER.info("We are open letter from ss.lv");

        LOGGER.info("We are going to link");
        LOGGER.info("We are input new password");


        LOGGER.info("We are closing our browser");
        //driver.quit();
    }

    private WebDriver getDriver() {
        LOGGER.info("Setting global property for driver");
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");

        LOGGER.info("Opening FireFox browser");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        return driver;

    }

}
