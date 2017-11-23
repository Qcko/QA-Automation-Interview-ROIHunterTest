import org.openqa.selenium.WebDriver;
import java.util.Scanner;

public class BrowserSelection {
    /** lets you choose which browser you want to run it in **/
    public static WebDriver getBrowser () {
        System.out.println("Which browser should i use? FirefoxDriverBuilder or ChromeDriveBuilder");
        Scanner keyboardInput = new Scanner(System.in);
        char whichBrowser = keyboardInput.nextLine().charAt(0);
        keyboardInput.close();
        switch (whichBrowser) {
            case 'F':
                System.out.println("FirefoxDriverBuilder chosen");
                return FirefoxDriverBuilder.inFirefox();
            case 'f':
                System.out.println("FirefoxDriverBuilder chosen");
                return FirefoxDriverBuilder.inFirefox();
            case 'C':
                System.out.println("ChromeDriveBuilder chosen");
                return ChromeDriveBuilder.inChrome();
            case 'c':
                System.out.println("ChromeDriveBuilder chosen");
                return ChromeDriveBuilder.inChrome();
        }
        System.out.println("Chosen browser not recognized");
        return null;
    }
}
