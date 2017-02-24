import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SwimMain extends Application
{
    public static void main(String[] args)
    {
        Members members = Members.getInstance();
        members.loadMembersToArrayList();
        SystemMenu.systemMenu();
        Application.launch(SystemMenu.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}