package manager;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private ChromeOptions chromeOptions;
    HelperUser helperUser;
    HelperCar helperCar;

    public void init(){
        chromeOptions = new ChromeOptions().addArguments("--lang=en");
        wd = new ChromeDriver(chromeOptions);
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
    }

    public void stop(){
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

}
