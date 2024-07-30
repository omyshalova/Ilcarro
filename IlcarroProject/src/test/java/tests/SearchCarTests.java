package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Rehovot", "7/30/2024", "7/31/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot", "10/15/2024", "10/10/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Rehovot", "10/15/2024", "10/10/2025");
        app.getHelperCar().submit();

    }




}
