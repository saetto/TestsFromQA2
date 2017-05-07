import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrei Pazniak on 5/1/2017.
 */
public class FindArticleDelfiCountComments {
    private static final Logger LOGGER = Logger.getLogger(DelfiTest.class);

    @Test
    public void findArticleDelfiCountComment() throws InterruptedException {

        LOGGER.info("We are starting our test");
        WebDriver driver = getDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        LOGGER.info("We are opening rus.delfi.lv home page");
        driver.get("http://rus.delfi.lv");

        LOGGER.info("We are found Article's name which we want find");
        String SearchAticle = "Директор ФБР: \"наибольшую угрозу\" представляет Россия";

        Boolean isClicled = false;
        for (int i = 0; i<10; i++ ) {
            WebElement web1 = driver.findElements(By.cssSelector("a[class='top2012-title']")).get(i);
                if(web1.getText().startsWith(SearchAticle)) {
                web1.click();
                isClicled=true;
                break;
            }
        }

            Assert.assertTrue("Dont't match", isClicled!=false);


        WebElement CommentCount=driver.findElement(By.className("comment-count"));
        String CommentCountString = CommentCount.getText();
        Integer countCommentGeneral = Integer.parseInt(CommentCountString.substring(1, CommentCountString.length()-1));

        LOGGER.info("Open page with comment");

        CommentCount.click();

        LOGGER.info("Open page with registered comment");
        WebElement RegisteredComment=driver.findElement(By.cssSelector("a[class='comment-thread-switcher-list-a comment-thread-switcher-list-a-reg']"));
        RegisteredComment.click();

        LOGGER.info("Find count registered comment");
        Integer RegisteredCommentCount=CalculaeteCommentCount(driver);

        LOGGER.info("Open page with unregistered comment");
        WebElement UnregisteredComment=driver.findElement(By.cssSelector("a[class='comment-thread-switcher-list-a comment-thread-switcher-list-a-anon']"));
        UnregisteredComment.click();

        LOGGER.info("Find count unregistered comment");
        Integer UnregisteredCommentCount=CalculaeteCommentCount(driver);

        Assert.assertFalse("Comment count are wrong",countCommentGeneral==RegisteredCommentCount+UnregisteredCommentCount);
    }

    private Integer CalculaeteCommentCount(WebDriver driver){
        Integer commentCount =  driver.findElements(By.className("comment-content-inner")).size();

        List<WebElement> nextCommentPage=driver.findElements(By.cssSelector("span[class='comments-images']"));
        if( nextCommentPage.size()==1) {
            nextCommentPage.get(0).click();
            return commentCount + CalculaeteCommentCount(driver);
        }
        else
            return commentCount;
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

