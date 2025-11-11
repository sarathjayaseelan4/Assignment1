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
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (Exception e) {
            System.err.println("Failed during setup: " + e.getMessage());
            Assert.fail("Browser setup failed", e);
        }
    }

    @Test
    public void count() {
        try {
            driver.get("https://open.spotify.com/");

            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-testid='search-input']")));
            search.click();

            search.sendKeys("lofi");

            search.sendKeys(Keys.ENTER);

            WebElement playlist = driver.findElement(By.linkText("Lofi Songs (Sukoon Vibes) \uD83D\uDC9C"));
            playlist.click();

            String list = driver.findElement(By.cssSelector("span.gRs15KFAiBRH4FFbVvVr:nth-child(1)")).getText();

            String check = "Lofi Songs (Sukoon Vibes) 💜";

            WebElement words = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-view']/div/div[2]/div[1]/div/main/section/div[1]/div[2]/div[3]/span[2]/h1")));
            String actualHeaderText = words.getText();

            Assert.assertEquals(actualHeaderText, check, "NOt matched");

            System.out.println(list);
        } catch (Exception e) {
            System.err.println("Test failed during execution: " + e.getMessage());
            Assert.fail("Test failed with exception", e);
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
