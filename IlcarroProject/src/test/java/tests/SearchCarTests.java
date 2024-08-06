package tests;

import manager.DataProviderDates;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SearchCarTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        app.getHelperCar().navigateByLogo();
    }

    @Test
    public void searchCurrentMonthSuccess(){
        LocalDate now = LocalDate.now();
        String dateFrom = String.format("%s/%s/%s", now.getMonthValue(), now.getDayOfMonth()+1, now.getYear());
        String dateTo = String.format("%s/%s/%s", now.getMonthValue(), now.getDayOfMonth()+2, now.getYear());
        logger.info("Dates: from - {} to - {}", dateFrom, dateTo);
        app.getHelperCar().searchCurrentMonth("Rehovot", dateFrom, dateTo);
//        app.getHelperCar().getScreen("src/test/screenshots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
        logger.info("Assert: Search-result table is present");
    }

    @Test
    public void searchCurrentYearSuccess(){
        LocalDate now = LocalDate.now();
        String dateFrom = String.format("%s/%s/%s", now.getMonthValue(), now.getDayOfMonth()+1, now.getYear());
        String dateTo = String.format("%s/%s/%s", now.getMonthValue()+1, now.getDayOfMonth(), now.getYear());
        logger.info("Dates: from - {} to - {}", dateFrom, dateTo);
        app.getHelperCar().searchCurrentYear("Rehovot", dateFrom, dateTo);
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
        logger.info("Assert: Search-result table is present");
    }

    @Test(dataProvider = "searchAnyPeriodPositive", dataProviderClass = DataProviderDates.class)
    public void searchAnyPeriodSuccess(String city, String dateFrom, String dateTo){
        logger.info("Dates: from - {} to - {}", dateFrom, dateTo);
        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().getScreen("src/test/screenshots/currentAnydate.png");
        app.getHelperCar().submit();
        logger.info("Assert: Search-result table is present");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test(dataProvider = "datesCSV", dataProviderClass = DataProviderDates.class)
    public void searchAnyPeriodSuccessFile(String city, String dateFrom, String dateTo){
        logger.info("Dates: from - {} to - {}", dateFrom, dateTo);
        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().getScreen("src/test/screenshots/currentAnydate.png");
        app.getHelperCar().submit();
        logger.info("Assert: Search-result table is present");
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Rehovot", "6/26/2024", "9/8/2024");
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperCar().getErrorText(), "You can't pick date before today");

    }

}
