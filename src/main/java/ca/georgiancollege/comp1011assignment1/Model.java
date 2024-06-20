package ca.georgiancollege.comp1011assignment1;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model extends BaseModel{

    private int id, year, month, average_players;

    public Model(){}

    public ResultSet getResults() throws SQLException {
        return getAllRows(getTable());
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getAverage_players() {
        return average_players;
    }
}
