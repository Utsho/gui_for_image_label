import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class gui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent parent=fxmlLoader.load();
        guiController controller=fxmlLoader.getController();






        Scene scene=new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Time Waste");
        primaryStage.show();

        controller.setWindow(primaryStage.getOwner());




    }

    public static void main(String[] args) {
        Application.launch();

    }




}
