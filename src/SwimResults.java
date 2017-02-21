import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.time.LocalDate;



public class SwimResults
{
    private SwimResult swimResult;
    Members members = Members.getInstance();


    public void resultToFile(SwimResult swimResult, String source, String ageGroup)
    {
        PrintStream output;
        String fileName              = source + "_" + ageGroup + ".txt";

        try
        {
            output = new PrintStream(new FileOutputStream(fileName, true));
        }

        catch (FileNotFoundException ex)
        {
            System.out.println("Kan ikke finde filen");
            System.out.println(ex);
            return;
        }

        output.print  (swimResult.getMemberId());
        output.print  ("," + swimResult.getName());
        output.print  ("," + swimResult.getLastName() );
        output.print  ("," + swimResult.getDisciplin());
        output.print  ("," + swimResult.getTime());
        output.print  ("," + swimResult.getDate());
        output.print  ("," + swimResult.getJuniorOrSenior());
        output.print  ("," + swimResult.getIsEvent());
        output.println("," + swimResult.getEventName());
    }

    public void saveResult(boolean isEvent)
    {
        CompetitionTeams competitionTeams = new CompetitionTeams();
        double time = 0;
        String disciplin = "";
        LocalDate date = LocalDate.now();
        String name = "";
        String lastName;
        int memberId = 0;
        boolean juniorOrSenior = true;
        String ageGroup = "";
        String eventName = "";
        String str = "";

        Scanner scanner;
        scanner                             = new Scanner(System.in);
        int     input                       = 0;
        boolean running                     = true;

        while (running == true)
        {
            members.showMembers();
            System.out.println();
            System.out.println("Indtast ID-nummer");

            memberId = scanner.nextInt();

            name = members.getMember(memberId).name();
            lastName = members.getMember(memberId).lastName();

            if(members.getMember(memberId).age() >= 18)
            {
                //SENIOR
                ageGroup = "senior";
                juniorOrSenior = true;
            }
            else
            {
                //JUNIOR
                ageGroup = "junior";
                juniorOrSenior = false;
            }

            System.out.println("Du har valgt: " + name + " " + lastName);
            System.out.println("* * * * * * * * * discipliner * * * * * * * *");
            System.out.println("* [1] Butterfly       [2] Crawl             *");
            System.out.println("* [3] Rygcrawl        [4] BrystSvoemning    *");
            System.out.println("* [5] Hundesvoemning  [6] Tilbage           *");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

            input = scanner.nextInt();

            switch (input)
            {
                case 1://
                    disciplin = "Butterfly";
                    break;

                case 2://
                    disciplin = "Crawl";
                    break;

                case 3://
                    disciplin = "Rygcrawl";
                    break;

                case 4://
                    disciplin = "BrystSvoemning";
                    break;

                case 5://
                    disciplin = "Hundesvoemning";
                    break;

                case 6://
                    competitionTeams.teamMenu();
                    break;

                default:
                    System.out.println(" --- Tast et tal mellem 1-6, proev igen ---");
                    break;
            }


            System.out.println("Indtast resultat [ss.mmm]");
            time = scanner.nextDouble() / 1000;



           // date.format();
            if(isEvent)
            {
                System.out.println("Indtast stævne navn");
                eventName = scanner.next();
                swimResult = new SwimResult(time, disciplin, date.toString(), name, lastName, memberId, juniorOrSenior, eventName, isEvent);


            }
            else
            {
                swimResult = new SwimResult(time, disciplin, date.toString(), name, lastName, memberId, juniorOrSenior, "Svoemmeklubben", isEvent);
            }
            resultToFile(swimResult, disciplin, ageGroup);

            System.out.println  ("ID: " + swimResult.getMemberId());
            System.out.print  ("Navn: " + swimResult.getName() );
            System.out.println  (" "+ swimResult.getLastName() );
            System.out.println  ("Disiplin: " + swimResult.getDisciplin());
            System.out.println  ("Tid: " + swimResult.getTime() + " Sekunder");
            System.out.println  ("Sat: " + swimResult.getDate());
            //System.out.print  ("," + swimResult.getJuniorOrSenior());
            //System.out.print  ("," + swimResult.getIsEvent());
            System.out.println("Sted: " + swimResult.getEventName());

            running = false;
        }


    }

    public void topFive(String disciplin, String ageGroup)
    {

        ArrayList<SwimResult> resultsList = new ArrayList<SwimResult>();

        String source = disciplin + "_" + ageGroup + ".txt";
        File f = new File(source);
        Scanner scanner;
        int length;
        double time;
        int memberId;
        boolean juniorOrSenior;
        boolean isEvent;

        try
        {
            scanner = new Scanner(f, StandardCharsets.UTF_8.name());
        }
        catch(FileNotFoundException ex)
        {
            return; // FEJL
        }

        while(scanner.hasNextLine())
        {
                String line = scanner.nextLine();
                String[] arr = line.split(","); // TODO - Check hvor mange den splitter

                memberId = Integer.parseInt(arr[0]);
                time = Double.parseDouble(arr[4]);
                juniorOrSenior = Boolean.parseBoolean(arr[6]);
                isEvent = Boolean.parseBoolean(arr[8]);

                swimResult = new SwimResult(time, arr[3], arr[5], arr[1], arr[2], memberId, juniorOrSenior, arr[7], isEvent);

                resultsList.add(swimResult);
        }

        for(int k = 0; k < resultsList.size(); k++)
        {
            System.out.println(resultsList.get(k).getName() + " " + resultsList.get(k).getTime());
        }

        System.out.println();
        int size = resultsList.size();
        SwimResult temp;

        SwimResult[] resultArray = new SwimResult[resultsList.size()];
        resultArray = resultsList.toArray(resultArray);



        for(int i = 0; i < size; i++)
        {
            for(int j = 1; j < (size - i); j++)
            {
                if(resultArray[j - 1].getTime() > resultArray[j].getTime())
                {
                    temp = resultArray[j - 1];
                    resultArray[j-1] = resultArray[j];
                    resultArray[j] = temp;
                }
            }
        }
        System.out.println("* * * TOP 5 " + disciplin + " * * * ");


        if(resultArray.length < 4)
        {
            length = resultArray.length;
        }
        else
        {
            length = 4;
        }

        for(int p = 0; p <= length; p++)
        {
            System.out.println((p + 1) + ". " + resultArray[p].getName() + " " + resultArray[p].getLastName() + " - Tid: " + resultArray[p].getTime() + "sek");
        }
        System.out.println();

    }
}


