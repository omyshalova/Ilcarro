package tests;

import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class AddNewCarTests extends TestBase{

    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
         app.getHelperUser().login(new User().setEmail("testolga@gmail.com").setPassword("Test1101!"));
        }
    }

    @Test
    public void AddNewCarSuccess(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2002")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900"+i)
                .price(50)
                .about("Nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        app.getHelperCar().pause(500);

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(), car.getManufacture()+" "+car.getModel()+" added successful");
    }

    @Test
    public void AddNewCarSuccess1(){
        int i = new Random().nextInt(1000)+1000;
        Car car = Car.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M6")
                .year("2003")
                .fuel("Gas")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900"+i)
                .price(50)
                .about("Nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm(car);
        app.getHelperCar().submit();

        Assert.assertTrue(app.getHelperCar().getMessage().contains("added successful"));
        Assert.assertEquals(app.getHelperCar().getMessage(), car.getManufacture()+" "+car.getModel()+" added successful");
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperCar().returnToHomePage();
    }
}
