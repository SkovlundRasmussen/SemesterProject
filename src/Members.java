import java.time.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Members
{
    private static Members instance = new Members();
    private ArrayList<MemberObject> members = new ArrayList<MemberObject>();
    private Members() {}
    private Toolbox tool = new Toolbox();

    public static Members getInstance()
    {
        return instance;
    }

    public MemberObject getMember(int index)
   {
      return members.get(index);   
   }

    public ArrayList getMembers()
   {
      return members;
   }

    public void loadMembersToArrayList()
    {
        String source = "memberList.txt";
        File f = new File(source);
        Scanner scanner;
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
        int age;
        int memberId;
        int paymentYear;
        boolean memberStatus;
        boolean active;
        boolean deletedMember;

            if(!scanner.hasNext())
            {
                return;
            }
            else
            {
                String line = scanner.next();
                String[] arr = line.split(","); // TODO - Check hvor mange den splitter

                age = Integer.parseInt(arr[2]);
                memberId = Integer.parseInt(arr[3]);
                memberStatus = Boolean.parseBoolean(arr[4]);
                active = Boolean.parseBoolean(arr[5]);
                paymentYear = Integer.parseInt(arr[6]);
                deletedMember = Boolean.parseBoolean(arr[7]);

                MemberObject member = new MemberObject(arr[0], arr[1], age, memberId, memberStatus, active, paymentYear, deletedMember);

                members.add(member);
            }
        }
    }

    public int getLastId()
    {
    int memberId = 0;

        for(int i = 0; i < members.size(); i++)
        {
            memberId = members.get(i).id();
        }
        return memberId;
    }

    public void showMembers()
    {
       for (int i = 0; i < members.size(); i++)
       {
           if (members.get(i).getDeletedMember() == true)
           {
               System.out.print("* Medlems Nummer:        ");
               System.out.println(members.get(i).id());

               System.out.print("* Navn:                  ");
               System.out.println(members.get(i).name() + " " + members.get(i).lastName());

               System.out.print("* Alder:                 ");
               System.out.println(members.get(i).age());

               System.out.print("* Medlemsstatus:         ");
               if (members.get(i).memberStatus() == true)
               {
                   System.out.println("Konkurrencesvoemmer");
               }
               else
               {
                   System.out.println("Motionist");
               }

               System.out.print("* Aktivitetsstatus:      ");
               if (members.get(i).active() == true)
               {
                   System.out.println("Aktiv");
               }
               else
               {
                   System.out.println("passiv");
               }

               System.out.print("* Sidste betalingsaar:   ");
               System.out.println(members.get(i).getPaymentYear() + " ");

               System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
           }
       }
   }

    public void createMember()
    {
        members = getMembers();
        final int   NAME          = 1;
        final int   LAST_NAME     = 2;
        final int   AGE           = 3;
        final int   ID            = 4;
        final int   MEMBER_STATUS = 5;
        final int   ACTIVE        = 6;
        String      name          = "";
        String      lastName      = "";
        String      input         = "";
        int         age           = 0;
        int         id            = getLastId();
        int         steps         = NAME;
        int         lastPayment   = 0;
        boolean     memberStatus  = true;
        boolean     active        = true;
        boolean     running       = true;
        Scanner     sc;
        MemberObject memberInfo;
        Toolbox tools = new Toolbox();

        sc = new Scanner(System.in);

        while (running) {
            switch (steps) {
                case 1:
                    System.out.println("Indtast fornavn");
                    name = sc.next();
                    steps = LAST_NAME;
                    break;
                case 2:
                    System.out.println("Indtast efternavn");
                    lastName = sc.next();
                    steps = AGE;
                    break;
                case 3:
                    System.out.println("Indtast foedselsaar [YYYY]");
                    age   = tools.getAgeFromBirthday(sc.nextInt())       ;
                    steps = ID;
                    break;
                case 4:
                    id++;
                    steps = MEMBER_STATUS;
                    break;
                case 5:
                    System.out.println("motion- eller konkurrencesvoemmer?              ");
                    System.out.println("[y] motionssvoemmer   [n] konkurrencesvoemmer   ");
                    input = sc.next();

                    if (input.equals("y"))
                    {
                        memberStatus = true;
                    }
                    else
                    {
                        memberStatus = false;
                    }
                    steps = ACTIVE;
                    break;
                case 6:
                    System.out.println("Indtast om medlem er aktiv eller passiv ");
                    System.out.println("     [y] Aktiv   [n] Passiv             ");
                    input = sc.next();

                    if (input.equals("y"))
                    {
                        active = true;
                    }
                    else
                    {
                        active = false;
                    }

                    steps = 7;
                    break;

                case 7:
                    LocalDate today   = LocalDate.now();
                    lastPayment       = today.getYear();

                    steps = 8;
                    break    ;

                case 8:
                    //Her opretter vi et nyt medlem.
                    memberInfo = new MemberObject(name, lastName, age, id, memberStatus, active, lastPayment, true);

                    System.out.println("Navn:                 " + memberInfo.name()         + " " + memberInfo.lastName()   );
                    System.out.println("Alder:                " + memberInfo.age()          + " "                           );
                    System.out.println("Medlems_ID:           " + memberInfo.id()           + " "                           );
                    System.out.println("Medlems Status:       " + memberInfo.memberStatus() + " "                           );
                    System.out.println("Aktivt medlem:        " + memberInfo.active()                                       );
                    System.out.println("Sidste betalings aar: " + memberInfo.getPaymentYear()                               );
                    System.out.println();

                    // Her ligger vi det nye medlem ind i arrayListen (memberList)
                    members.add(memberInfo)  ;
                    memberToFile(memberInfo) ;

                    running = false      ; // Stop while loopet
                    break                ;
            }
        }
    }

    public void editMember(Boolean delete)
    {
        boolean running = true;
        String str      = "";
        int memberID;
        int input       = 0;
        Scanner scanner;
        scanner         = new Scanner(System.in);

        showMembers();
        System.out.println();
        System.out.println("Indtast ID-nummer");
        memberID        = scanner.nextInt();
        if(delete == true)
        {
            getMember(memberID).setDeletedMember(false);
        }
        else {

            while (running) {

                System.out.println("* * * * * * * * * * MENU *  * * * * * * * * *");
                System.out.println("* [1] Fornavn    [2] Efternavn              *");
                System.out.println("* [3] Alder      [4] Medlems status         *");
                System.out.println("* [5] Aktivitet  [6] Betalings aar          *");
                System.out.println("* [7] Tilbage                               *");
                System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

                input = Integer.parseInt(scanner.next());

                switch (input) {
                    case 1:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Navn der skal redigeres: " + members.get(i).name());
                                str = scanner.next();
                                members.get(i).setName(str);
                            }
                        }

                        break;

                    case 2:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Efternavn der skal redigeres: " + members.get(i).lastName());
                                str = scanner.next();
                                members.get(i).setLastName(str);
                            }
                        }
                        break;

                    case 3:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Alder der skal redigeres: " + members.get(i).age());
                                System.out.println("* Angiv ønsket fødselsaar [yyyy]: ");
                                str = scanner.next();
                                members.get(i).setAge(Integer.parseInt(str));
                            }
                        }
                        break;

                    case 4:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Medlemsstatus der skal redigeres: " + members.get(i).memberStatus());
                                System.out.println("* [true] = Konkurrencesvoemmer | [false] = Motionist");
                                str = scanner.next();
                                members.get(i).setMemberStatus(Boolean.parseBoolean(str));
                            }
                        }
                        break;

                    case 5:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Medlemsaktivitet der skal redigeres : " + members.get(i).active());
                                System.out.println("* [true] = Aktiv | [false] = Passiv");
                                str = scanner.next();
                                members.get(i).setActive(Boolean.parseBoolean(str));
                            }
                        }
                        break;

                    case 6:
                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).id() == memberID) {
                                System.out.println("* Sidste betalingsaar der skal redigeres:  " + members.get(i).getPaymentYear());
                                str = scanner.next();
                                members.get(i).setLastPayment(Integer.parseInt(str));
                            }
                        }
                        break;

                    case 7:
                        AdminstrerMedlemMenu.adminstrerMedlemMenu();
                        break;

                    default:
                        System.out.println(" --- Tast et tal mellem 1-5, proev igen ---");
                        break;
                }
                membersToFile();
            }
        }
    }


    public void membersToFile()
    {
        PrintStream output;
        String source              = "memberList.txt";

        try
        {
            output = new PrintStream(new File("memberList.txt"));
        }

        catch (FileNotFoundException ex)
        {
            System.out.println("Kan ikke finde filen");
            System.out.println(ex);
            return;
        }

        for (int i = 0; i < members.size(); i++)
        {
            output.print  (members.get(i).name());
            output.print  ("," + members.get(i).lastName() );
            output.print  ("," + members.get(i).age());
            output.print  ("," + members.get(i).id());
            output.print  ("," + members.get(i).memberStatus());
            output.print  ("," + members.get(i).active());
            output.print  ("," + members.get(i).getPaymentYear());
            output.println("," + members.get(i).getDeletedMember());
        }
    }

    public void memberToFile(MemberObject member) //Gemmer medlem i en fil.
    {
        PrintStream output;
        String source              = "memberList.txt";

        try
        {
            output = new PrintStream(new FileOutputStream("memberList.txt", true));
        }

        catch (FileNotFoundException ex)
        {
            System.out.println("Kan ikke finde filen");
            System.out.println(ex);
            return;
        }

        output.print  (member.name());
        output.print  ("," + member.lastName() );
        output.print  ("," + member.age());
        output.print  ("," + member.id());
        output.print  ("," + member.memberStatus());
        output.print  ("," + member.active());
        output.print  ("," + member.getPaymentYear());
        output.println("," + member.getDeletedMember());
    }

    public void getMembersInRestance()
    {
        int year = LocalDate.now().getYear();
        int memberAge = 0;
        int payment = 0;
        int paymentYear = 0;
        int restanceYear = 0;
        for (int i = 0; i < members.size(); i++)
        {
            if(members.get(i).getDeletedMember())
            {
                if (year > members.get(i).getPaymentYear())
                {
                    memberAge = members.get(i).age();
                    paymentYear = members.get(i).getPaymentYear();
                    restanceYear = year - paymentYear;

                    if(members.get(i).active() == true)
                    {



                        if (memberAge < 18) {
                            payment = 1000 * restanceYear;

                        } else if (memberAge > 60) {
                            payment = 1200 * restanceYear;
                        } else {
                            payment = 1600 * restanceYear;
                        }

                    }
                    else
                    {
                        payment = 500 * restanceYear;
                    }
                    System.out.print(members.get(i).name() + " " + members.get(i).lastName() + " har sidst betalt i aar ");

                    System.out.print(members.get(i).getPaymentYear() + ", manglende betaling ");

                    System.out.println(payment + ",-");




                }
                else
                {
                   // System.out.println("Der er ingen medlemmer i restance");
                }
            }
        }
    }
}