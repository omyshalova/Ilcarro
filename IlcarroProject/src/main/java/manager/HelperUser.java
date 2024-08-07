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

    //Registration

    public void openRegistrationForm() {
        click(By.xpath("//*[text()=' Sign up ']"));
    }

    public void fillRegistrationFrom(String name, String lastName, String email, String password){
        type(By.xpath("//input[@id='name']"), name);
        type(By.xpath("//input[@id='lastName']"), lastName);
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
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
        if (!wd.findElement(By.id("terms-of-use")).isSelected()) {
            WebElement label = wd.findElement(By.cssSelector("label[for='terms-of-use']"));
            Rectangle rect = label.getRect();
            int width = rect.getWidth();
//        Dimension size = wd.manage().window().getSize();
            int xOffSet = -width / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(label, xOffSet, 0).click().release().perform();
        }
    }

    public boolean isWrongPasswordFormat() {
        String messege = wd.findElement(By.xpath("//div[contains(text(),'Password must contain')]")).getText();
        return messege.contains("Password must contain");
    }

    public String getEmptyLastNameFieldMessage() {
        return wd.findElement(By.xpath("//div[text()=' Last name is required ']")).getText();
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOk();
    }
}
