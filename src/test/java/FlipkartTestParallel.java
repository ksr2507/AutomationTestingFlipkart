import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlipkartTestParallel {
    private WebDriver driver;
    private WebDriverWait wait;
    public static final String UserName="vidya_Lujw2m";
    public static final String AutomateKey="pu7zCBRM8AVVAcFeKJVg";
    public static final String URL="https://"+UserName+":"+AutomateKey+"@hub-cloud.browserstack.com/wd/hub";
    @BeforeClass
    @Parameters({"browser", "browserVersion", "os","device"})
    public void setUp(String browser,@Optional("") String browserVersion,@Optional("") String os,@Optional("") String device) throws MalformedURLException {
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions caps = new ChromeOptions();
            caps.setBrowserVersion(browserVersion);
            caps.setPlatformName(os);
            driver = new RemoteWebDriver(new URL (URL),caps);
        }
        else if(browser.equalsIgnoreCase("firefox")){
            FirefoxOptions caps = new FirefoxOptions();
            caps.setPlatformName(os);
            caps.setBrowserVersion(browserVersion);
            driver = new RemoteWebDriver(new URL (URL),caps);
        }
        else if(browser.equalsIgnoreCase("edge")){
            EdgeOptions caps = new EdgeOptions();
            caps.setPlatformName(os);
            caps.setBrowserVersion(browserVersion);
            driver = new RemoteWebDriver(new URL (URL),caps);
        }
        else if(browser.equalsIgnoreCase("safari")) {
            SafariOptions caps = new SafariOptions();
            caps.setCapability("platformName",os);
            caps.setBrowserVersion(browserVersion);
            driver = new RemoteWebDriver(new URL(URL), caps);
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Test
    public void testFlipkartSearchAndFilter() {
        // 1. Open flipkart.com
        driver.get("https://www.flipkart.com");

        // 3. Search for the product "Samsung Galaxy S10"
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Samsung Galaxy S10");
        searchBox.submit();

        // 4. Click on "Mobiles" in categories
        WebElement mobilesCategory = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title, 'Mobiles')]")));
        mobilesCategory.click();

        // 5. Apply the Samsung Brand Filter
        WebElement samsungFilter = wait.until(ExpectedConditions.elementToBeClickable(By.className("XqNaEv")));
        samsungFilter.click();

        // 6. Sort the entries with Price -> High to Low
        WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'Price -- High to Low')]")));
        sortDropdown.click();

        // 5. Selecting the Flipkart Assured Filter
        WebElement flipkartAssuredFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='XqNaEv eJE9fb']")));
        flipkartAssuredFilter.click();

        // 7. Reading page 1
        // Allow time for the page to load the results
        try {
            Thread.sleep(5000); // Wait for page load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 8. Create a list with the specified parameters and print it
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='cPHDOP col-12-12']//div[@class='_75nlfW']"));
        for (WebElement product : products) {
            try {
                WebElement productNameElement = product.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                WebElement displayPriceElement = product.findElement(By.xpath(".//div[@class='Nx9bqj _4b5DiR']"));
                WebElement productLinkElement = product.findElement(By.xpath(".//a"));

                String productName = productNameElement.getText();
                String displayPrice = displayPriceElement.getText();
                String productLink = productLinkElement.getAttribute("href");

                System.out.println("Product Name: " + productName);
                System.out.println("Display Price: " + displayPrice);
                System.out.println("Link to Product Details Page: " + productLink);
                System.out.println("----------------------------------------");
            } catch (Exception e) {
                System.out.println("Error extracting product information: " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
