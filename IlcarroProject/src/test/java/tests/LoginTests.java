package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.lang.model.element.TypeElement;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().loggout();
        }
    }

    @Test
    public void loginSuccess1(){
        User user = new User().setEmail("testolga@gmail.com").setPassword("Test1101!");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("testolga@gmail.com", "Test1101!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    @Test
    public void loginSuccessModel(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("testolga@gmail.com", "Test1101!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
    }

    //Negative tests
    @Test
    public void loginWrongEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("testlgagmail.com", "Test1101!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("testolga@gmail.com", "Test");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @Test
    public void  loginUnregisteredUser(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("tutu@gmail.com", "Tutu1101!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
    }

    @Test
    public void loginEmptyEmail(){
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("", "Test1101!");
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getErrorText(), "Email is required");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
//    public void loginEmptyPassword(){
//        app.getHelperUser().openLoginForm();
//        app.getHelperUser().fillLoginForm("testolga@gmail.com", "");
//        app.getHelperUser().submitLogin();
//
//        Assert.assertEquals(app.getHelperUser().getErrorText(), "Password is required");
//        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
//    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOk();
    }

}
