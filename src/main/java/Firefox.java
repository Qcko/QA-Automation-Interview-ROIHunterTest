import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox {
    /** Builds firefox driver **/

    public static WebDriver inFirefox() {
        try {
            WebDriver driver = new FirefoxDriver();
            return driver;
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to firefoxDriver is not set in system variables!!");
        }
        catch (WebDriverException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to firefox is not set in system variables!!");
        }
    return null;
    }
}
