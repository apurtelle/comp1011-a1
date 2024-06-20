package ca.georgiancollege.comp1011assignment1;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableController extends BaseController {

    @FXML
    private TableView<Model> table;
    @FXML
    private TableColumn table_id;
    @FXML
    private TableColumn table_year;
    @FXML
    private TableColumn table_month;
    @FXML
    private TableColumn table_average_players;

    public TableController(String title, String viewFile) {
        super(title, viewFile);
    }

    @FXML
    void initialize() {
        try {
            Model model = new Model();
            ResultSet results = model.getResults();
            while (results.next()) {
                int id = results.getInt("id");
                int year = results.getInt("year");
                int month = results.getInt("month");
                int average_players = results.getInt("average_players");

                table_id.setCellValueFactory(new PropertyValueFactory("id"));
                table_year.setCellValueFactory(new PropertyValueFactory("year"));
                table_month.setCellValueFactory(new PropertyValueFactory("month"));
                table_average_players.setCellValueFactory(new PropertyValueFactory("average_players"));

                System.out.println("id:" + id + ", year:" + year + ", month:" + month + ", average_players:" + average_players);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception when loading data for the table.");
        }
    }



    @FXML
    private void onBtnChartClick() {
        try {
            ChartController controller = new ChartController("Destiny 2 Player Count", "chart");
            controller.openPage();
        } catch (IOException e) {
            System.out.println("There was an IO Exception upon switching to the chart.");
        }
    }
}
