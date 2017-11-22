import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class bigChromeTests {
    public Browser browser = new Browser();
    public WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setBrowser() {
        Browser bigChrome = new Browser();
        bigChrome.setName("bigChrome");
        bigChrome.setType(BrowserTypes.CHROME);
        bigChrome.setWidth(1300);
        browser = bigChrome;
        driver.manage().window().setSize(new Dimension(browser.getWidth(), 980));
    }


}
