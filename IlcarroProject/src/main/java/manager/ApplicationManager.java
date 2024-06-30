package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    WebDriver wd;

    public void init(){
        wd = new ChromeDriver();
        wd.navigate().to("https://ilcarro.web.app/login?url=%2Flet-car-work");
    }

    public void stop(){
        wd.quit();
    }

}
