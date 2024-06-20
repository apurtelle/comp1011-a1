package ca.georgiancollege.comp1011assignment1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        ChartController controller = new ChartController("Destiny 2 Player Count", "chart");
        controller.setStage(stage);
        controller.openPage();
    }

    public static void main(String[] args) {
        launch();
    }
}