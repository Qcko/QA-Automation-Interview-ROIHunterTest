import org.openqa.selenium.WebDriver;


public class Main {
    public static void main(String[] args) {
        try {
            WebDriver driver = BrowserSelection.getBrowser();                    //chooses browser
            GooglePage.goToPage(driver);                                //goes to google page
            GooglePage.searchFor(driver, "t-mobile");                //searches for t-mobile
            GooglePage.goToLink(driver, "T-Mobile.cz");              //clicks link with T-Mobile.cz
            //removes acceptCookieUseAgreement acceptance request
            TMobileHomepage.acceptCookieUseAgreement(driver);
            TMobileHomepage.goToLink(driver);              //goes to podpora page
            TMobileContactUsPage.goToLink(driver, "formul");         //clicks link with Kontaktni formular

            //fills up the form
            TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
            TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
            TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
            TMobileContactUsFormPage.fillInField(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
            TMobileContactUsFormPage.checkCheckbox(driver, "span.like-checkbox.ico-check-mark");
            TMobileContactUsFormPage.uploadFile(driver, "//*[contains(text(), 'Vybrat soubor')]");      //clicks button and inserts a file
            TMobileContactUsFormPage.submitForm(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
            driver.close();

        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
