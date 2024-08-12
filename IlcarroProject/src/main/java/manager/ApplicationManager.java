package manager;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    EventFiringWebDriver wd;

    String browser;
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    private OperaOptions operaOptions;

    HelperUser helperUser;
    HelperCar helperCar;

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init(){

        if (browser.equals(BrowserType.CHROME)){
            chromeOptions = new ChromeOptions().addArguments("--lang=en");
            wd = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
            logger.info("All tests are done in Chrome Browser");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            firefoxOptions = new FirefoxOptions().addArguments("--lang=en");
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("All tests are done in Firefox Browser");
        } else if (browser.equals(BrowserType.EDGE)){
            wd = new EventFiringWebDriver(new EdgeDriver());
            logger.info("All tests are done in Edge Browser");
        } else if (browser.equals(BrowserType.OPERA)){
            operaOptions = new OperaOptions().addArguments("--lang=en");
            wd = new EventFiringWebDriver(new OperaDriver());
            logger.info("All tests are done in Opera Browser");
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");

        logger.info("The link: " + wd.getCurrentUrl());

        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);

        wd.register(new ListenerWD(logger));
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
