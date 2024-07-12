package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    //Login

    public void openLoginForm(){
        click(By.xpath("//a[@ng-reflect-router-link='login']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void fillLoginForm(User user){
        type(By.xpath("//input[@id='email']"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void submit(){
        click(By.xpath("//button[@type='submit']"));
    }

    public void loggout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public void clickOk(){
        if (isElementPresent(By.xpath("//button[@type='button']")))
        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public String getMessage(){
        pause(1000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
//        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
//        boolean res1 = wd.findElement(By.cssSelector("button[type='submit']"));
        return !wd.findElement(By.cssSelector("button[type='submit']")).isEnabled();
    }

    //Registration

    public void openRegistrationForm() {
        click(By.xpath("//*[text()=' Sign up ']"));
    }

    public void fillRegistrationFrom(User user) {
        type(By.id("name"), user.getFirstName());
        type(By.id("lastName"), user.getLastName());
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }

    public void checkPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    public void checkPolicyXY(){
        WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
        Rectangle rect = label.getRect();
        int width = rect.getWidth();

//        Dimension size = wd.manage().window().getSize();

        int xOffSet = -width/2;
        Actions actions = new Actions(wd);
        actions.moveToElement(label, xOffSet, 0).click().release().perform();

    }
}
