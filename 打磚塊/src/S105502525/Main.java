package S105502525;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	gmanager Gmanager = new gmanager();

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("scene3.fxml"));
    	FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("scene2.fxml"));
    	FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("scene1.fxml"));
    	FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("start.fxml"));
        Controller controller_scene1 = new Controller (Gmanager);
       
        startcontroller controller_start = new startcontroller ();
        game_controller controller_scene2 = new  game_controller (Gmanager);
        game_controller2 controller_scene3 = new  game_controller2 (Gmanager);
        fxmlLoader4.setController(controller_scene3);
        fxmlLoader3.setController(controller_scene2);
        fxmlLoader2.setController(controller_scene1);
        fxmlLoader1.setController(controller_start);
        Parent root4 = fxmlLoader4.load();
        Parent root3 = fxmlLoader3.load();
        Parent root2 = fxmlLoader2.load();
        Parent root1 = fxmlLoader1.load();
       
        primaryStage.setTitle("Warrior War");
        Scene scene3 = new Scene ( root4, 500, 600 );
        Scene scene2 = new Scene ( root3, 500, 600 );
        Scene scene1 = new Scene ( root2, 600, 400 );
        Scene scene_start = new Scene ( root1, 600, 400 );
        controller_scene3.setScene ( scene3);
         controller_scene1.setScene ( scene1,scene2,primaryStage );
         controller_start.sScene ( scene1 ,primaryStage);
         controller_scene2.setScene(scene2,scene3,primaryStage );
         primaryStage.setScene(scene_start);
         primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
