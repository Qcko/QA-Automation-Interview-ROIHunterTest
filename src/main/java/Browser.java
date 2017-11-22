import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    private String name;
    private int width;
    private BrowserTypes type;

    public void setName (String nameVar) {
        name = nameVar;
    }
    public void setWidth (int widthVar) {
        width = widthVar;
    }
    public void setType (BrowserTypes typeVar) {
        type = typeVar;
    }
    public String getName () {
        return name;
    }
    public int getWidth () {
        return width;
    }
    public WebDriver getType () {
        switch (type) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();

        }
        return null;
    }
}
