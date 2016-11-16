package apploader;

import controller.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        primaryStage.setTitle("Welcome!");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("css/stylesheet.css");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/view/WelcomeScreen.fxml"));
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



    public static void main(String[] args) {
        ConfigLoader.loadConfig();
        launch(args);
    }
}
