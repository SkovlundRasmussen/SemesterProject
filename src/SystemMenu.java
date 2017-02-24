import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.*;
public class SystemMenu extends Application
{
    Button button;

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
        Application.launch("sdadsa");

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        AdminstrerMedlemMenu adminMedlem    = new AdminstrerMedlemMenu();
        primaryStage.setTitle("Hovedmenu");

        button = new Button();
        button.setText("Adminstrer medlem");
        button.setOnAction(event ->
        {
            adminMedlem.adminstrerMedlemMenu();
        });

        StackPane layout = new StackPane();

        layout.getChildren().addAll(button);
        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
