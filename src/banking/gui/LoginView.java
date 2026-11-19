package banking.gui;

import banking.database.DatabaseTables;
import banking.model.CustomerRegistry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginView extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Create database tables if they do not exist
        DatabaseTables.createTables();

        // Load saved customers and accounts from SQLite
        CustomerRegistry.loadFromDatabase();

        // Load login screen
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("LoginView.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Access Bank Botswana");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
