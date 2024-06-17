package ca.georgiancollege.comp1011assignment1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DBUtility {

    private String connectionString, username, password, database;
    private String table;

    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;

    public DBUtility(String database) {
        this.database = database;
        connectionString = "jdbc:mysql://database-1.c5gysuweuzrh.us-east-1.rds.amazonaws.com:3306";
        username = "admin";
        password = "";

        try{
            password = Files.readString(Path.of("src/main/resources/ca/georgiancollege/comp1011assignment1/data/.pwd"));
            connection = DriverManager.getConnection(
                    connectionString + "/" + database,
                    username, password);
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
    public DBUtility(String database, String table){
        this(database);
        setTable(table);
    }
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void queryExec(String sql) throws Exception{
        statement = connection.createStatement();
        statement.execute(sql);
    }
    public ResultSet queryResult(String sql) throws SQLException{

        preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public ResultSet getRowById(String table, int id) throws SQLException{

        preparedStatement = connection.prepareStatement("select * from "+table+" where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet deleteRowById(String table, int id) throws SQLException{

        preparedStatement = connection.prepareStatement("delete from "+table+" where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }


    public ResultSet getAllRows(String table) throws SQLException {
        return queryResult("select * from " + table);
    }
    public ResultSet getAllRowsOrderBy(String table, String column, String direction) throws SQLException {
        return queryResult("select * from " + table + " order by " + column + " " + direction);
    }



}

