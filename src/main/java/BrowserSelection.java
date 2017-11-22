import org.openqa.selenium.WebDriver;
import java.util.Scanner;

public class BrowserSelection {
    /** lets you choose which browser you want to run it in **/
    public static WebDriver getBrowser () {
        System.out.println("Which browser should i use? Firefox or Chrome");
        Scanner keyboardInput = new Scanner(System.in);
        char whichBrowser = keyboardInput.nextLine().charAt(0);
        keyboardInput.close();
        switch (whichBrowser) {
            case 'F':
                System.out.println("Firefox chosen");
                return Firefox.inFirefox();
            case 'f':
                System.out.println("Firefox chosen");
                return Firefox.inFirefox();
            case 'C':
                System.out.println("Chrome chosen");
                return Chrome.inChrome();
            case 'c':
                System.out.println("Chrome chosen");
                return Chrome.inChrome();
        }
        System.out.println("Chosen browser not recognized");
        return null;
    }
}
