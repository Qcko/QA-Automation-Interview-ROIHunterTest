import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
    /**handles operations on google page**/
    private static String url = "http://www.google.cz";

    public static void goToPage(WebDriver driver) throws Exception {
        driver.get(url);
    }

    public static void searchFor(WebDriver driver, String s) throws Exception {
        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys(s);
        searchField.submit();
    }

    public static void goToLink(WebDriver driver, String s) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(s)));

        WebElement link = driver.findElement(By.partialLinkText(s));
        link.click();
    }
}
