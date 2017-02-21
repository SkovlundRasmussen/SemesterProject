import java.util.*;
public class SystemMenu
{
    public static void systemMenu()
    {
        Scanner scanner;
        scanner                             = new Scanner(System.in);
        int     input                       = 0;
        boolean running                     = true;
        AdminstrerMedlemMenu adminMedlem    = new AdminstrerMedlemMenu();
        CompetitionTeams competitionTeams   = new CompetitionTeams();
        Members members                     = Members.getInstance();
        Toolbox tool                        = new Toolbox();

        while (running == true)
        {
            System.out.println("* * * * * * * * * * MENU *  * * * * * * * * *");
            System.out.println("* [1] Adminstrer medlem     [2] Resultater  *");
            System.out.println("* [3] Restance              [4] Afslut      *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

            input = tool.convertToInt();

            switch (input)
            {
                case 1://Admin medlem
                    adminMedlem.adminstrerMedlemMenu();
                    break;
                case 2://resultater
                    competitionTeams.teamMenu();
                    break;
                case 3://restance
                    members.getMembersInRestance();
                    break;
                case 4://afslut
                    System.out.println("Lavet af: *Peter* *Jamie* *Kristian* *Nichlas*");
                    System.exit(1);
                    break;
                default:
                    System.out.println(" --- Tast et tal mellem 1-4, proev igen ---");
                    break;
            }
        }
    }
}
