import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class bigFirefoxTests {
    public Browser browser = new Browser();
    public WebDriver driver = new FirefoxDriver();

    @BeforeClass
    public void setBrowser() {
        Browser bigFirefox = new Browser();
        bigFirefox.setName("bigFirefox");
        bigFirefox.setType(BrowserTypes.FIREFOX);
        bigFirefox.setWidth(1300);
        browser = bigFirefox;
        driver.manage().window().setSize(new Dimension(browser.getWidth(), 980));
    }


}
