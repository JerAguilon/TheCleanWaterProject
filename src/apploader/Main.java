package apploader;

import controller.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(new Scene(root));

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/Welcome.fxml"));
        loader.load();
        WelcomeScreenController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();

        /*FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Welcome.fxml"));
        Parent root = (Parent)loader.load();
        WelcomeScreenController controller = loader.getController();
        controller.initialize();
        controller.setMainApp(this);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/
    }

    public void showLoginScene() throws Exception {
        Stage stage = new Stage();
        stage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.print("HELLO");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
