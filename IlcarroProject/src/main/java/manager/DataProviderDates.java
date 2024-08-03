package manager;

import org.testng.annotations.DataProvider;

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
        //object to be added
        return list.iterator();
    }
}
