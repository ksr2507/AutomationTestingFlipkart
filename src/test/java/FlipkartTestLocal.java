import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlipkartTestLocal {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
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
