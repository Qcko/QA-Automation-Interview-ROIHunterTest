import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome {
/** Builds chrome driver **/

    public static WebDriver inChrome() {
        try {
            WebDriver driver = new ChromeDriver();
            return driver;
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to chromeDriver is not set in system variables!!");
        }
        catch (WebDriverException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to chrome is not set in system variables!!");
        }
    return null;
    }
}
