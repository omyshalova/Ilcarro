package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCars {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> example1() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("link to file (root repository)")));
        String line = reader.readLine();
        while (line!=null){
            String[] all = line.split(";");
            list.add(new Object[]{});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> carCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cars.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] all = line.split(";");

            list.add(new Object[]{Car.builder()
                    .location(all[0])
                    .manufacture(all[1])
                    .model(all[2])
                    .year(all[3])
                    .fuel(all[4])
                    .seats(Integer.parseInt(all[5]))
                    .carClass(all[6])
                    .carRegNumber(all[7])
                    .price(Double.parseDouble(all[8]))
                    .about(all[9])
                    .photoLink(all[10])
                    .build()});
            line = reader.readLine();

        }
        return list.iterator();
    }
}
