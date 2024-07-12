package tests;

import models.Car;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCarTests extends TestBase{

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
                .price(50000)
                .about("Nice car")
                .build();

        app.getHelperCar().openCarForm();
        app.getHelperCar().fillCarForm();
        app.getHelperCar().submitCarForm();

    }
}
