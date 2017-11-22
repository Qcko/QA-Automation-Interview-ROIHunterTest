import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class zkouskaTest {
    public WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Test(groups = "form")
    public void test() {
        String result = "form-control success-fisGeld";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        try {
            TMobileContactUsForm.sent(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
        } catch (Exception e) {
        }
        //after sending the new form is pulled up
        //so checking if the form after send is new
        Verify.verify(!driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).getAttribute("class").contains(result));
    }
}
