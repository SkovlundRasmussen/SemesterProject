import java.util.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class Toolbox
{
    public int convertToInt()
    {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean error = true;

        while (error)
        {
            try
            {
                input = Integer.parseInt(scanner.next());
                error = false;

            } catch (NumberFormatException e) {
                System.out.println("Fejl! Du skal indtaste et heltal, proev igen.");
            }
        }

        return input;
    }


    public int getAgeFromBirthday(int birthYear) // Retunere en alder, ud fra hvilket årstal de er født i.
    {
        int age;
        LocalDate today      = LocalDate.now();
        LocalDate birthday   = LocalDate.of(birthYear, Month.JANUARY, 1);
        Period p             = Period.between(birthday, today);
        age                  = p.getYears();

        return age;
    }
}
