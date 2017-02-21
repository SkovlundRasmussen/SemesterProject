import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class GetAgeFromBirthday
{
    public int getAgeFromBirthday(int birthYear)
    {
        int age;
        LocalDate today      = LocalDate.now();
        LocalDate birthday   = LocalDate.of(birthYear, Month.JANUARY, 1);
        Period p             = Period.between(birthday, today);
        age                  = p.getYears();

        return age;
    }
}
