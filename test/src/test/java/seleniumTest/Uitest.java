package seleniumTest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Uitest {
    public static void main(String[] args) {
      
        
        WebDriverManager.chromedriver().setup();
		
        WebDriver driver = new ChromeDriver();
        
        try {
            // Step 1: Open browser and navigate to eBay
            driver.get("https://www.ebay.com");
            driver.manage().window().maximize();

            // Step 2: Search for 'book'
            WebElement searchBox = driver.findElement(By.id("gh-ac"));
            searchBox.sendKeys("book");
            searchBox.submit();

            // Step 3: Click on the first book in the list
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".s-item a")));
            firstItem.click();

            // Step 4: Add the item to the cart
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("atcRedesignId_btn")));
            addToCartButton.click();

            // Step 5: Verify the cart is updated
            WebElement cartCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gh-minicart span")));
            String cartCount = cartCountElement.getText();
            
            if (cartCount.equals("1")) {
                System.out.println("Test passed: Item successfully added to the cart.");
            } else {
                System.out.println("Test failed: Cart count is not as expected. Cart count: " + cartCount);
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
