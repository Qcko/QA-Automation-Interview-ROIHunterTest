import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TMobileContactUsPage {
    /** handles operations on contact us page**/
    public static void goToLink(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement link = driver.findElement(By.partialLinkText(s));
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();
    }
}
