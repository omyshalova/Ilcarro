package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderDates {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{});//object to be added
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> searchAnyPeriodPositive(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Rehovot", "8/15/2024", "8/25/2024"});
        list.add(new Object[]{"Rehovot", "8/15/2024", "10/25/2024"});
        list.add(new Object[]{"Rehovot", "8/15/2024", "2/25/2025"});
        list.add(new Object[]{"Rehovot", "9/15/2024", "9/25/2024"});
        list.add(new Object[]{"Rehovot", "9/15/2024", "10/25/2024"});
        list.add(new Object[]{"Rehovot", "9/15/2024", "2/25/2025"});
        list.add(new Object[]{"Rehovot", "2/15/2025", "2/25/2025"});
        list.add(new Object[]{"Rehovot", "2/15/2025", "3/25/2025"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> datesCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/dates.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] all = line.split(";");
            list.add(new Object[]{all[0], all[1], all[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
