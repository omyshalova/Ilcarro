package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.xpath("//a[@ng-reflect-router-link='login']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }

    public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
    }

    public void loggout() {
        click(By.xpath("//a[text()=' Logout ']"));
    }

    public void clickOk(){
        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public String getMessage(){
        pause(1000);
        return wd.findElement(By.cssSelector(".dialog-container>h2")).getText();
    }

}
