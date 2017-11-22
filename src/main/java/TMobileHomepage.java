import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TMobileHomepage {
/**handles operation on t-mobile homepage**/
    public static void goToLink(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //if browser width is too small menu buttons are in drop out menu
        Boolean maxSize = new Boolean(true);
        try {
            if (driver.findElement(By.cssSelector("body > div.header > div > div > div.navbar-header > button > svg")).isDisplayed()) {
                maxSize = false;
            }
        } catch (NoSuchElementException e) {}

        //todo refactor without new boolean creation

        if (maxSize == false) {
            //menu button is on screen of browser
            WebElement menu = driver.findElement(By.cssSelector("body > div.header > div > div > div.navbar-header > button > svg"));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.header > div > div > div.navbar-header > button > svg")));
            menu.click();
            WebElement submenu = driver.findElement(By.xpath("//*[@id=\"navsecondlel\"]/li[5]/a"));
            submenu.click();
            WebElement newLink = driver.findElement(By.xpath("//*[@id=\"navsecondlel\"]/li[5]/ul/li[3]/a"));
            newLink.click();
        } else {
            //menu button is not on screen of browser
            wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Podpora")));
            WebElement link = driver.findElement(By.partialLinkText("Podpora"));
            Actions hover = new Actions(driver);
            hover.moveToElement(link).build().perform();
            link.click();
            hover.release();
            TMobileHelp.goToLink(driver, "se na");                    //clicks link with Obratte se na nas
        }
    }

    public static void cookie(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement cookie = driver.findElement(By.id("cookieUseAgreementBtn"));
        wait.until(ExpectedConditions.elementToBeClickable(cookie));
        Cookie ck = new Cookie("cookieUseAgreement", "true");
        driver.manage().addCookie(ck);
        driver.navigate().refresh();
    }
}
