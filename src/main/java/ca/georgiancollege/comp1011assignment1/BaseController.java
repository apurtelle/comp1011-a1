package ca.georgiancollege.comp1011assignment1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseController {

    private Stage stage;
    private String viewFile, title;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public BaseController(String title, String viewFile){
        this.title = title;
        this.viewFile = viewFile;
    }
    void openPage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(viewFile + "-view.fxml"));

        fxmlLoader.setController(this);

        Scene scene = new Scene(fxmlLoader.load());
        if(stage == null)
            setStage(new Stage());

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
