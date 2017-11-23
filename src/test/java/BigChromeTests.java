import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BigChromeTests {
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

    @Test(dependsOnMethods = "GooglePage_searchFor_stringSearched")
    public void GooglePage_goToLink_pageLoaded() throws Exception {
        /**search for t-mobile and then click the link**/
        String result = "www.t-mobile.cz";
        driver.get("http://www.google.cz");
        GooglePage.searchFor(driver, "t-mobile");                //searches for t-mobile
        GooglePage.goToLink(driver, "T-Mobile.cz");              //clicks link with T-Mobile.cz
        Verify.verify(driver.getCurrentUrl().contains(result));
    }

    @Test
    public void TMobileHomepage_acceptCookieUseAgreement_popUpNotDisplayed() {
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
    public void TMobileContactUsFormPage_fillInSubjectField_fieldFilledIn() {
        /**checks if you can fill up subject field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void TMobileContactUsFormPage_fillInTextField_fieldFilledIn() {
        /**checks if you can fill up text field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent")).getAttribute("class").contains(result));
    }

    @Test(groups = "form")
    public void TMobileContactUsFormPage_fillInPhoneNumberField_fieldFilledIn() {
        /**checks if you can fill up phone number field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber")).getAttribute("class").contains(result));
    }

    @Test(dependsOnMethods = "TMobileContactUsFormPage_fillInPhoneNumberField_fieldFilledIn")
    public void TMobileContactUsFormPage_fillInPhoneNumberField_errorForNotSubmittingEnoughNumbers() {
        /**checks if phone number field not 6-12 numbers gives error**/
        String result = "Prosím, zadejte od 6 do 12";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "1234");
        //clears focus on that field so there is displayed if the field is empty in class variable
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
    }

    @Test(dependsOnMethods = "TMobileContactUsFormPage_fillInPhoneNumberField_fieldFilledIn")
    public void TMobileContactUsFormPage_fillInPhoneNumberField_errorForNotSubmittingNumbers() {
        /**checks if phone number field gives error when not only numbers are inserted**/
        String result = "Prosím, zadejte pouze čísla";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "a2bc");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
    }

    @Test(groups = "form")
    public void TMobileContactUsFormPage_fillInEmailField_fieldFilledIn() {
        /**checks if you can fill up email field**/
        String result = "form-control success-field";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        Verify.verify(driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail")).getAttribute("class").contains(result));
    }

    @Test(dependsOnMethods = "TMobileContactUsFormPage_fillInEmailField_fieldFilledIn")
    public void TMobileContactUsFormPage_fillInEmailField_errorForNotSubmittingValidEmailFormat() {
        /**checks if email field gives error when not valid email format is inserted**/
        String result = "Prosím, zadejte platný e-mail.";
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post");
        //clears focus on that field so javascript on the page validates the field
        driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject")).click();
        WebElement childElement = driver.findElement(By.id("ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail"));
        WebElement parentElement = childElement.findElement(By.xpath("./.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./div"));
        Verify.verify(newChildElement.getText().contains(result));
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

    @Test
    public void TMobileContactUsFormPage_submitForm_errorForNotFillingUpAllRequiredFields() {
        /**checks if form was not send when required fields are not filled up **/
        String result = "https://www.t-mobile.cz/podpora/kontaktujte-nas";
        String errorMsg = "form-control error-field";
        Boolean errorMsgExists = false;
        driver.get("https://www.t-mobile.cz/podpora/kontaktujte-nas");
        TMobileHomepage.acceptCookieUseAgreement(driver);

        //if there exist required field (they are marked by magenta * on the page)
        //click that field to run down javascript on the page to mark all required fields that are empty with error msg
        outerLoop1:
        for (WebElement element :
                driver.findElements(By.cssSelector("span[class='text-magenta']"))
                ) {
            WebElement parentElement = element.findElement(By.xpath("./../.."));
            for (WebElement el :
                    parentElement.findElements(By.xpath(".//*"))
                    ) {
                if (el.getTagName().contains("input")) {
                    el.submit();
                    break outerLoop1;
                }
                if (el.getTagName().contains("textarea")) {
                    el.submit();
                    break outerLoop1;
                }
            }
        }

        //if there is still some required field with error msg change boolean to true
        outerLoop2:
        for (WebElement element :
                driver.findElements(By.cssSelector("span[class='text-magenta']"))
                ) {
            WebElement parentElement = element.findElement(By.xpath("./../.."));
            for (WebElement el :
                    parentElement.findElements(By.xpath(".//*"))
                    ) {
                if (el.getTagName().contains("input")) {
                    if (el.getAttribute("class").contains(errorMsg)) {
                        errorMsgExists = true;
                        break outerLoop2;
                    }
                }
                if (el.getTagName().contains("textarea")) {
                    if (el.getAttribute("class").contains(errorMsg)) {
                        errorMsgExists = true;
                        break outerLoop2;
                    }
                }
            }
        }

        try {
            TMobileContactUsFormPage.submitForm(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        //if boolean is true the form should not submit i.e. stays at the form page
        if (errorMsgExists == true) {
            Verify.verify(driver.getCurrentUrl().equals(result));
        } else {
            System.out.println("All required fields are properly filled up, so form was submitted.");
            Verify.verify(false);
        }
    }

    @AfterClass
    public void cleanUp() {
        driver.close();
    }
}
