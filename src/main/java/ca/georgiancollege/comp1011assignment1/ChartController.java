package ca.georgiancollege.comp1011assignment1;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChartController extends BaseController {

    @FXML
    private CategoryAxis chartX;
    @FXML
    private NumberAxis chartY;
    @FXML
    private LineChart<CategoryAxis, NumberAxis> chart;
    @FXML
    private Button btnTable;

    public ChartController(String title, String viewFile) {
        super(title, viewFile);
    }

    @FXML
    void initialize() {
        try {
            Model model = new Model();
            XYChart.Series series = new XYChart.Series();

            // populate the line chart
            ResultSet results = model.getResults();
            addDataPoints(series, results);
            chart.getData().add(series);
        } catch (SQLException e) {
            System.out.println("SQL Exception when loading data for the chart.");
        }
    }

    public void addDataPoint(XYChart.Series series, String date, int avg_players) {
        series.getData().add(new XYChart.Data(date, avg_players));
    }

    public String convertDate(int month, int year) {
        String monthStr = "";
        switch(month) {
            case(1):
                monthStr = "Jan";
                break;
            case(2):
                monthStr = "Feb";
                break;
            case(3):
                monthStr = "Mar";
                break;
            case(4):
                monthStr = "Apr";
                break;
            case(5):
                monthStr = "May";
                break;
            case(6):
                monthStr = "Jun";
                break;
            case(7):
                monthStr = "Jul";
                break;
            case(8):
                monthStr = "Aug";
                break;
            case(9):
                monthStr = "Sep";
                break;
            case(10):
                monthStr = "Oct";
                break;
            case(11):
                monthStr = "Nov";
                break;
            case(12):
                monthStr = "Dec";
                break;
        }
        return monthStr + ", " + Integer.toString(year);
    }

    public void addDataPoints(XYChart.Series series, ResultSet results) throws SQLException {
        while (results.next()) {
            int id = results.getInt("id");
            int year = results.getInt("year");
            int month = results.getInt("month");
            int average_players = results.getInt("average_players");
            String date = convertDate(month, year);

            addDataPoint(series, date, average_players);
        }
    }

    @FXML
    private void onBtnTableClick() {
        try {
            TableController controller = new TableController("Destiny 2 Player Count Table", "table");
            controller.openPage();
        } catch (IOException e) {
            System.out.println("There was an IO Exception upon switching to the table.");
        }
    }
}