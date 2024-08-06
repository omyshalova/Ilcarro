import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class trial {

    public static void main(String[] args){
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getMonthValue());
        System.out.println(now.getYear());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.minusDays(-7).format(DateTimeFormatter.ofPattern("M/d/yyyy")).toString());
        String dateFrom = String.format("%s/%s/%s", now.getMonthValue(), now.getDayOfMonth()+1, now.getYear());
        System.out.println(dateFrom);

    }


}
