package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().loggout();
        }
    }

    //Positive

    @Test
    public void registrationSuccess(){
        int i = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popins" + i + "@gmail.com")
                .setPassword("Popins1235813!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }

    //Negative

    @Test
    public void registrationWrongEmailFormat(){
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popinsgmail.com")
                .setPassword("Popins1235813!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();


        softAssert.assertEquals(app.getHelperUser().getWrongEmailFormatMessage(), "Wrong email format");
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationEmptyEmailField(){
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("")
                .setPassword("Popins1235813!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        softAssert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationWrongPasswordFormat(){
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popins@gmail.com")
                .setPassword("Popins123581");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        softAssert.assertTrue(app.getHelperUser().isWrongPasswordFormat());
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationEmptyPasswordField(){
        int i = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popins"+i+"@gmail.com")
                .setPassword("");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        softAssert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationEmptyNameField(){
        int i = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("")
                .setLastName("Popins")
                .setEmail("popins"+i+"@gmail.com")
                .setPassword("Popins123581!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword());
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        softAssert.assertEquals(app.getHelperUser().getEmptyNameFieldMessage(), " Name is required ");
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationEmptyLastNameField(){
        int i = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("Mary")
                .setLastName("")
                .setEmail("popins"+i+"@gmail.com")
                .setPassword("Popins123581!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();

        softAssert.assertEquals(app.getHelperUser().getEmptyLastNameFieldMessage(), " Last name is required ");
        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }

    @Test
    public void registrationPolicyButtonNotChecked(){
        int i = (int)(System.currentTimeMillis()/1000)%36000;
        User user =new User()
                .setFirstName("Mary")
                .setLastName("Popins")
                .setEmail("popins" + i + "@gmail.com")
                .setPassword("Popins1235813!");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationFrom(user);
        app.getHelperUser().submit();

        softAssert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();
    }
}
