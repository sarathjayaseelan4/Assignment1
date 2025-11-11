import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class clone {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        try {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--start-maximized");
            driver = new ChromeDriver(opt);
        } catch (Exception e) {
            System.err.println("Failed to initialize ChromeDriver: " + e.getMessage());
            Assert.fail("Failed to initialize ChromeDriver", e);
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriverWait: " + e.getMessage());
            Assert.fail("Failed to initialize WebDriverWait", e);
        }

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            System.err.println("Failed to set implicit wait: " + e.getMessage());
            Assert.fail("Failed to set implicit wait", e);
        }
    }

    @Test
    public void count() {
        WebElement search = null;
        WebElement playlist = null;
        String list = null;
        WebElement words = null;
        String actualHeaderText = null;
        String check = "Lofi Songs (Sukoon Vibes) 💜";

        try {
            driver.get("https://open.spotify.com/");
        } catch (Exception e) {
            System.err.println("Failed to navigate to URL: " + e.getMessage());
            Assert.fail("Failed to navigate to URL", e);
        }

        try {
            search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-testid='search-input']")));
        } catch (Exception e) {
            System.err.println("Failed to find search input: " + e.getMessage());
            Assert.fail("Failed to find search input", e);
        }

        try {
            search.click();
        } catch (Exception e) {
            System.err.println("Failed to click search input: " + e.getMessage());
            Assert.fail("Failed to click search input", e);
        }

        try {
            search.sendKeys("lofi");
        } catch (Exception e) {
            System.err.println("Failed to send keys 'lofi': " + e.getMessage());
            Assert.fail("Failed to send keys 'lofi'", e);
        }

        try {
            search.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.err.println("Failed to send ENTER key: " + e.getMessage());
            Assert.fail("Failed to send ENTER key", e);
        }

        try {
            playlist = driver.findElement(By.linkText("Lofi Songs (Sukoon Vibes) \uD83D\uDC9C"));
        } catch (Exception e) {
            System.err.println("Failed to find playlist by link text: " + e.getMessage());
            Assert.fail("Failed to find playlist by link text", e);
        }

        try {
            playlist.click();
        } catch (Exception e) {
            System.err.println("Failed to click playlist: " + e.getMessage());
            Assert.fail("Failed to click playlist", e);
        }

        try {
            list = driver.findElement(By.cssSelector("span.gRs15KFAiBRH4FFbVvVr:nth-child(1)")).getText();
        } catch (Exception e) {
            System.err.println("Failed to get text from CSS selector: " + e.getMessage());
            Assert.fail("Failed to get text from CSS selector", e);
        }

        try {
            words = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-view']/div/div[2]/div[1]/div/main/section/div[1]/div[2]/div[3]/span[2]/h1")));
        } catch (Exception e) {
            System.err.println("Failed to find header element: " + e.getMessage());
            Assert.fail("Failed to find header element", e);
        }

        try {
            actualHeaderText = words.getText();
        } catch (Exception e) {
            System.err.println("Failed to get text from header: " + e.getMessage());
            Assert.fail("Failed to get text from header", e);
        }

        try {
            Assert.assertEquals(actualHeaderText, check, "NOt matched");
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
            Assert.fail("Assertion failed", e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during assertion: " + e.getMessage());
            Assert.fail("An unexpected error occurred during assertion", e);
        }

        try {
            System.out.println(list);
        } catch (Exception e) {
            System.err.println("Failed to print list: " + e.getMessage());
        }
    }

    @AfterClass
    public void clossss() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Failed during browser teardown: " + e.getMessage());
        }
    }
}