package tests;

import manager.DataProviderDates;
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

        app.getHelperCar().searchCurrentYear("Rehovot", "10/15/2024", "10/25/2024");
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test(dataProvider = "searchAnyPeriodPositive", dataProviderClass = DataProviderDates.class)
    public void searchAnyPeriodSuccess(String city, String dateFrom, String dateTo){
        app.getHelperCar().searchAnyPeriod(city, dateFrom, dateTo);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }




}
