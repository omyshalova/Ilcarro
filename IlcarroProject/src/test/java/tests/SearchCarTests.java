package tests;

import manager.DataProviderDates;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        app.getHelperCar().navigateByLogo();
    }

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "8/5/2024", "8/25/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){

        app.getHelperCar().searchCurrentYear("Rehovot", "10/15/2024", "10/25/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test(dataProvider = "searchAnyPeriodPositive", dataProviderClass = DataProviderDates.class)
    public void searchAnyPeriodSuccess(String city, String dateFrom, String dateTo){
        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().getScreen("src/test/screenshots/currentAnydate.png");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void negativeSearch(){
        app.getHelperCar().searchNotValidPeriod("Rehovot", "6/26/2024", "9/8/2024");
        Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperCar().getErrorText(), "You can't pick date before today");

    }

}
