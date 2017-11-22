import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class smallChromeTests {
    public Browser browser = new Browser();
    public WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setBrowser() {
        Browser smallChrome = new Browser();
        smallChrome.setName("smallChrome");
        smallChrome.setType(BrowserTypes.CHROME);
        smallChrome.setWidth(900);
        browser = smallChrome;
        driver.manage().window().setSize(new Dimension(browser.getWidth(), 980));
    }


}
