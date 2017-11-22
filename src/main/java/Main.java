import org.openqa.selenium.WebDriver;


public class Main {
    public static void main(String[] args) {
        try {
            WebDriver driver = BrowserSelection.getBrowser();                    //chooses browser
            GooglePage.goToPage(driver);                                //goes to google page
            GooglePage.searchFor(driver, "t-mobile");                //searches for t-mobile
            GooglePage.goToLink(driver, "T-Mobile.cz");              //clicks link with T-Mobile.cz
            //removes cookie acceptance request
            TMobileHomepage.cookie(driver);
            TMobileHomepage.goToLink(driver);              //goes to podpora page
            TMobileContactUsPage.goToLink(driver, "formul");         //clicks link with Kontaktni formular

            //fills up the form
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasubject", "DELETE: Pokusny dotaz");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQacontent", "Omlouvam se za zkouseni u vas");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaphoneNumber", "123456789");
            TMobileContactUsForm.fillIn(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQaemail", "pokusnejmail@post.cz");
            TMobileContactUsForm.checkBox(driver, "span.like-checkbox.ico-check-mark");
            TMobileContactUsForm.insertFile(driver, "//*[contains(text(), 'Vybrat soubor')]");      //clicks button and inserts a file
            TMobileContactUsForm.sent(driver, "ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQasend-button");
            driver.close();


        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
