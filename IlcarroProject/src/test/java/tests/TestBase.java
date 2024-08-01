package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    static ApplicationManager app = new ApplicationManager();

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setApp(){
        app.init();
    }

    @BeforeMethod
    public void startLogger(Method m){
        logger.info("   Start method {} ({})", m.getName(), m.toGenericString());
    }

    @AfterMethod
    public void endLogger(Method m){
        logger.info("*************************************************************");
    }

    @AfterSuite
    public void tearDown(){
        app.stop();
    }

    public void logUserData(String email, String password) {
        logger.info("Test data: email - {} & password - {}", email, password);
    }

}
