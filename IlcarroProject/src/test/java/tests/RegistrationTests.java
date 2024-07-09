package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().loggout();
        }
    }

    @Test
    public void registrationSuccess(){
        int i = new Random().nextInt(1000)+1000;
        int z = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popins" + z + "@gmail.com")
                .setPassword("Popins1235813!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();
    }
}
