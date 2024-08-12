package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setApp(){
        app.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void startLogger(Method m){
        logger.info("   Start method {} ({})", m.getName(), m.toGenericString());
    }

    @AfterMethod(alwaysRun = true)
    public void endLogger(Method m){
        logger.info("*************************************************************");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }

    public void logUserData(String email, String password) {
        logger.info("Test data: email - {} & password - {}", email, password);
    }

}
