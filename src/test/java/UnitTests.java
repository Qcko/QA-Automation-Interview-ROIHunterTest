import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class UnitTests {
    public WebDriver driver = new ChromeDriver();

    @Test
    public void GooglePage_goToPage_pageLoaded() throws Exception {
        /**if it went to google**/
        String result = "www.google.cz";
        GooglePage.goToPage(driver);
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void GooglePage_searchFor_stringSearched() throws Exception {
        /**search for t-mobile**/
        String result = "t-mobile";
        driver.get("http://www.google.cz");
        GooglePage.searchFor(driver, "t-mobile");
        Verify.verify(driver.findElement(By.id("lst-ib")).getAttribute("value").contains(result));
    }

    @Test
    public void TMobileHomepage_acceptCookieUseAgreement_popUpNotDisplayed() throws Exception {
        /**if there is acceptCookieUseAgreement agreement pop up send acceptCookieUseAgreement and check if pop up disappears after page refresh**/
        driver.get("https://www.t-mobile.cz/osobni");
        TMobileHomepage.acceptCookieUseAgreement(driver);
        Verify.verify(driver.findElements(By.id("cookieUseAgreementBtn")).isEmpty());
    }

    @Test(dependsOnMethods = "TMobileHomepage_acceptCookieUseAgreement_popUpNotDisplayed")
    public void TMobileHomepage_goToLink_pageLoaded() {
        /**goes to help page**/
        String result = "https://www.t-mobile.cz/podpora/obratte-se-na-nas";
        driver.get("https://www.t-mobile.cz/osobni");
        TMobileHomepage.goToLink(driver);
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void TMobileContactUsPage_goToLink_pageLoaded() {
        /**goes to contact us form**/
        String result = "https://www.t-mobile.cz/podpora/kontaktujte-nas";
        driver.get("https://www.t-mobile.cz/podpora/obratte-se-na-nas");
        TMobileContactUsPage.goToLink(driver, "formul");
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test(groups = "form")
    public void TMobileContactUsFormPage_fillInField_fieldFilledIn() {
        /**checks if fillInField metod works**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).getAttribute("class").contains(result));
    }

    @Test(groups = "form", dependsOnMethods = "TMobileHomepage_acceptCookieUseAgreement_popUpNotDisplayed")
    public void TMobileContactUsFormPage_checkCheckbox_checkboxIsChecked() {
        /**checks if you can check the checkbox**/
        String result = "active";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileHomepage.acceptCookieUseAgreement(driver);
        TMobileContactUsFormPage.checkCheckbox(driver, "span.like-checkbox.ico-check-mark");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).click();
        Verify.verify(driver.findElement(By.cssSelector("span.like-checkbox.ico-check-mark")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void TMobileContactUsFormPage_uploadFile_fileUploaded() throws Exception {
        /**checks if you can insert a file**/
        String result = "test.txt";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.uploadFile(driver, "//*[contains(text(), 'Vybrat soubor')]");      //clicks button and inserts a file
        Verify.verify(driver.findElement(By.xpath("//*[@id=\"_ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQa_fileList\"]")).getText().contains(result));
    }

    @Test(dependsOnGroups = "form")
    public void TMobileContactUsFormPage_submitForm_formSubmitted() throws Exception {
        /**checks if after filling up form and sending it, the page gives you new form**/
        String result = "https://www.t-mobile.cz/podpora/kontaktujte-nas";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileHomepage.acceptCookieUseAgreement(driver);
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
        TMobileContactUsFormPage.checkCheckbox(driver, "span.like-checkbox.ico-check-mark");
        TMobileContactUsFormPage.uploadFile(driver, "//*[contains(text(), 'Vybrat soubor')]");
        TMobileContactUsFormPage.submitForm(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
        //success msg is in hard to find element and have already submitted too many forms for the poor t-mobile
        //so i am checking it differently
        //after submitting the new form is pulled up which changes url
        //so i am checking if the url changed
        Verify.verify(!driver.getCurrentUrl().equals(result));
    }


    @AfterClass
    public void cleanUp() {
        driver.close();
    }
}
