import java.time.LocalDate;

public class main {

    public static void main(String[] args){
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getMonthValue());
        System.out.println(now.getYear());
        System.out.println(now.getDayOfMonth());
        System.out.println(now);
    }

}
