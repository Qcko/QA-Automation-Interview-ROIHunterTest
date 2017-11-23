import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TMobileContactUsFormPage {
    /**handles operations on contact us form**/
    public static void fillInField(WebDriver driver, String field, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement inputField = driver.findElement(By.id(field));
        wait.until(ExpectedConditions.visibilityOf(inputField));
        inputField.sendKeys(s);
    }

    public static void checkCheckbox(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement checkBox = driver.findElement(By.cssSelector(s));
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
    }

    public static void uploadFile(WebDriver driver, String s) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        //finds a button and hidden element to input file in
        WebElement button = driver.findElement(By.xpath(s));
        WebElement parentElement = button.findElement(By.xpath("./../.."));
        WebElement newChildElement = parentElement.findElement(By.xpath("./input"));
        newChildElement.sendKeys("D:\\dev\\workspace\\test.txt");

//        //put path to your image in a clipboard
//        StringSelection ss = new StringSelection("D:\\dev\\workspace\\test.txt");
//        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
//
//        //imitate mouse events CTRL+V,ENTER
//        Robot robot = new Robot();
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        // robot does not work if you select different window while it makes actions


        //wait till file is uploaded
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"_ContactFormPortlet_WAR_ibaczfaqportletportlet_INSTANCE_KlFUaBS7XQQa_fileList\"]")));
    }

    public static void submitForm(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = driver.findElement(By.id(s));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }



}
