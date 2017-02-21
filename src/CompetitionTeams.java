import java.util.Scanner;

public class CompetitionTeams
{
    SwimResults swimResult = new SwimResults();

    public void disciplinMenu(String disciplin) // TODO Ret Menuen til
    {
        Scanner scanner;
        scanner                             = new Scanner(System.in);
        int     input                       = 0;
        boolean running                     = true;

        Toolbox tool                        = new Toolbox();

        while (running == true) {
            System.out.println("* * * * * * * * * " + disciplin + " * * * * * * * * *");
            System.out.println("* [1] Top 5 Junior    [2] Top 5 Senior      *");
            System.out.println("* [3] Tilbage                               *");
            System.out.println("*                                           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

            input = Integer.parseInt(scanner.next());

            switch (input) {
                case 1:// Top 5 Junior
                    swimResult.topFive(disciplin, "junior");
                    break;

                case 2:// Top 5 Senior
                    swimResult.topFive(disciplin, "senior");
                    break;

                case 3:// Tilbage
                    discipliner();
                    break;

                default:
                    System.out.println(" --- Tast et tal mellem 1-3, proev igen ---");
                    break;
            }
        }
    }

    public void discipliner()
    {
        Scanner scanner;
        scanner                             = new Scanner(System.in);
        int     input                       = 0;
        boolean running                     = true;
        Toolbox tool                        = new Toolbox();

        while (running == true)
        {
            System.out.println("* * * * * * * * * discipliner * * * * * * * *");
            System.out.println("* [1] Butterfly       [2] Crawl             *");
            System.out.println("* [3] Rygcrawl        [4] BrystSvoemning    *");
            System.out.println("* [5] Hundesvoemning  [6] Tilbage           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

            input = Integer.parseInt(scanner.next());

            switch (input)
            {
                case 1://
                    disciplinMenu("Butterfly");
                    break;

                case 2://
                    disciplinMenu("Crawl");
                    break;

                case 3://
                    disciplinMenu("Rygcrawl");
                    break;

                case 4://
                    disciplinMenu("BrystSvoemning");
                    break;

                case 5://
                    disciplinMenu("Hundesvoemning");
                    break;

                case 6://
                    teamMenu();
                    break;

                default:
                    System.out.println(" --- Tast et tal mellem 1-6, proev igen ---");
                    break;
            }
        }
    }

    public void teamMenu()
    {
        SwimResults swimResults = new SwimResults();
        Scanner scanner;
        scanner                             = new Scanner(System.in);
        int     input                       = 0;
        boolean running                     = true;
        Toolbox tool                        = new Toolbox();


        while (running == true)
        {
            System.out.println("* * * * * * * * Holdmenu  * * * * * * * * * *");
            System.out.println("* [1] Traenings tid    [2] Staevne tid      *");
            System.out.println("* [3] Discipliner      [4] Hovedmenu        *");
            System.out.println("*                                           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

            input = Integer.parseInt(scanner.next());

            switch (input)
            {
                case 1://Træning
                    swimResults.saveResult(false);
                    break;

                case 2://Stævne
                    swimResults.saveResult(true);
                    break;

                case 3://Discipliner
                discipliner();
                    break;

                case 4://[4] Hovedmenu
                    SystemMenu.systemMenu();
                    break;

                default:
                    System.out.println(" --- Tast et tal mellem 1-5, proev igen ---");
                    break;
            }
        }
    }
}
