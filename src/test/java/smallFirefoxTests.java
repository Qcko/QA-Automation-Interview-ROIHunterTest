import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class smallFirefoxTests {
    public Browser browser = new Browser();
    public WebDriver driver = new FirefoxDriver();

    @BeforeClass
    public void setBrowser() {
        Browser smallFirefox = new Browser();
        smallFirefox.setName("smallFirefox");
        smallFirefox.setType(BrowserTypes.FIREFOX);
        smallFirefox.setWidth(900);
        browser = smallFirefox;
        driver.manage().window().setSize(new Dimension(browser.getWidth(), 980));
    }

}
