package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase{

    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());

        type(By.id("seats"), String.valueOf(car.getSeats()));

        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());

        type(By.id("price"), car.getPrice()+"");

        type(By.id("about"), car.getAbout());

    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");
    }

    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));
    }

    public void returnToHomePage(){
        click(By.xpath("//button[text()='Search cars']"));
    };

    public void attachPhoto(String link) {
        wd.findElement(By.cssSelector("#photos")).sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));
//        "7/27/2024", "7/30/2024/
        String[] from = dateFrom.split("/");
        String[] to = dateTo.split("/");

        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        String locatorTo = "//div[text()=' " + to[1] + " ']";

        click(By.xpath(locatorFrom));
        click(By.xpath(locatorTo));
    }

    private void typeCity(String city) {

        type(By.id("city"), city);
        pause(200);
        click(By.cssSelector("div.pac-item"));

    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        typeCity(city);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth = from.getMonthValue() -  now.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if(diffMonth>0){
            clickNextMonthBtn(diffMonth);
        }
        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    private void clickNextMonthBtn(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));
        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        //{"Rehovot", "2/15/2024", "3/25/2025"}

        typeCity(city);
        click(By.id("dates"));

        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));

        int diffMonth;
        int diffYear = from.getYear() - now.getYear();
        if (diffYear==0){
            diffMonth = from.getMonthValue() - now.getMonthValue();
        }else {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locatorFrom = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locatorFrom));

        diffYear = to.getYear() - from.getYear();
        if (diffYear==0){
            diffMonth = to.getMonthValue() - from.getMonthValue();
        }else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthBtn(diffMonth);
        String locatorTo = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locatorTo));
    }
}
