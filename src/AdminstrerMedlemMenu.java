import java.util.*;

public class AdminstrerMedlemMenu
{
    public static void adminstrerMedlemMenu()
    {
       Members members  = Members.getInstance();
       int input        = 0;
       boolean running  = true;
       Scanner scanner  = new Scanner(System.in);
       Toolbox tool     = new Toolbox();

       while (running)
       {
           System.out.println("* * * * * * * * * * MENU *  * * * * * * * * *");
            System.out.println("* [1] Se medlemmer    [2] Opret medlem      *");
            System.out.println("* [3] Rediger medlem  [4] Slet medlem       *");
            System.out.println("* [5] Hovedmenuen                           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

           input = tool.convertToInt();

            switch (input) {
            case 1: //Se alle medlemmer
                members.showMembers();
                    break;
                case 2: //Opret medlem
                    members.createMember();
                    break;
                case 3: //Rediger medlem
                    members.editMember(false);
                    break;
                case 4: //Slet medlem
                    members.editMember(true);
                    break;
                case 5: //Tilbage
                    SystemMenu.systemMenu();
                    break;
                default:
                    System.out.println(" --- Tast et tal mellem 1-5, proev igen ---");
                    break;
            }
        }
    }
}