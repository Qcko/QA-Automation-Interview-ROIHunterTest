import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Tests {
    public WebDriver driver = new ChromeDriver();

    @Test
    public void testGoesToGooglePage() {
        /**if it went to google**/
        String result = "www.google.cz";
        try {
            GooglePage.goToPage(driver);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void testGoogleInsert() {
        /**search for t-mobile**/
        String result = "t-mobile";
        driver.get("http://www.google.cz");
        try {
            GooglePage.searchFor(driver, "t-mobile");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        Verify.verify(driver.findElement(By.id("lst-ib")).getAttribute("value").contains(result));
    }

    @Test(dependsOnMethods = "testGoogleInsert")
    public void testGoogleClickLink() {
        /**search for t-mobile and then click the link**/
        String result = "www.t-mobile.cz";
        driver.get("http://www.google.cz");
        try {
            GooglePage.searchFor(driver, "t-mobile");                //searches for t-mobile
            GooglePage.goToLink(driver, "T-Mobile.cz");              //clicks link with T-Mobile.cz
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void testCookies() {
        /**if there is cookie agreement pop up send cookie and check if pop up disappears after page refresh**/
        driver.get("https://www.t-mobile.cz/osobni");
        try {
            TMobileHomepage.cookie(driver);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        Verify.verify(driver.findElements(By.id("cookieUseAgreementBtn")).isEmpty());
    }

    @Test(dependsOnMethods = "testCookies")
    public void testNavigationTowardsHelpPage() {
        /**goes to help page**/
        String result = "https://www.t-mobile.cz/podpora/obratte-se-na-nas";
        driver.get("https://www.t-mobile.cz/osobni");
        TMobileHomepage.goToLink(driver);
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void testNavigationTowardsHelpForm() {
        /**goes to contact us form**/
        String result = "https://www.t-mobile.cz/podpora/kontaktujte-nas";
        driver.get("https://www.t-mobile.cz/podpora/obratte-se-na-nas");
        TMobileContactUsPage.goToLink(driver, "formul");
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test(groups = "form")
    public void testFillUpSubjectField() {
        /**checks if you can fill up subject field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void testFillUpTextField() {
        /**checks if you can fill up text field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void testFillUpPhoneField() {
        /**checks if you can fill up phone number field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber")).getAttribute("class").contains(result));
    }

    @Test(dependsOnMethods = "testFillUpPhoneField")
    public void testFillUpPhoneFieldWrongLength() {
        /**checks if phone number field not 6-12 numbers gives error**/
        String result = "Prosím, zadejte od 6 do 12";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "1234");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
    }

    @Test(dependsOnMethods = "testFillUpPhoneField")
    public void testFillUpPhoneFieldNotNumber() {
        /**checks if phone number field gives error when not only numbers are inserted**/
        String result = "Prosím, zadejte pouze čísla";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "a2bc");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
    }

    @Test(groups = "form")
    public void testFillUpEmailField() {
        /**checks if you can fill up email field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail")).getAttribute("class").contains(result));
    }

    @Test(dependsOnMethods = "testFillUpEmailField")
    public void testFillUpEmailFieldWrong() {
        /**checks if email field gives error when not valid email format is inserted**/
        String result = "Prosím, zadejte platný e-mail.";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
    }

    @Test(groups = "form", dependsOnMethods = "testCookies")
    public void testCheckingCheckbox() {
        /**checks if you can check the checkbox**/
        String result = "active";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsForm.checkBox(driver, "span.like-checkbox.ico-check-mark");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).click();
        Verify.verify(driver.findElement(By.cssSelector("span.like-checkbox.ico-check-mark")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void testInsertAFile() {
        /**checks if you can insert a file**/
        String result = "test.txt";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        try {
            TMobileContactUsForm.insertFile(driver, "//*[contains(text(), 'Vybrat soubor')]");      //clicks button and inserts a file
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        System.out.println(driver.findElement(By.xpath("//*[@id=\"_ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQa_fileList\"]")).getText());
        Verify.verify(driver.findElement(By.xpath("//*[@id=\"_ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQa_fileList\"]")).getText().contains(result));
    }

    @Test(dependsOnGroups = "form")
    public void testIfFormWasSent() {
        /**checks if after filling up form and sending it the page gives you new form**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        try {
            TMobileHomepage.cookie(driver);
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
            TMobileContactUsForm.checkBox(driver, "span.like-checkbox.ico-check-mark");
            TMobileContactUsForm.insertFile(driver, "//*[contains(text(), 'Vybrat soubor')]");
            TMobileContactUsForm.sent(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        //after sending the new form is pulled up
        //so checking if the form after send is new
        Verify.verify(!driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).getAttribute("class").contains(result));
    }

    @AfterClass
    public void cleanUp() {
        driver.close();
    }
}
