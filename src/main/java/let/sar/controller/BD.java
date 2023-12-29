package let.sar.controller;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class BD extends Cfg{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost+":"
                +dbPost+"/"+dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection= DriverManager.getConnection(connectionString,dbUser,
                dbPass);
        return dbConnection;
    }
    public void Add_player(String name, Integer score){
        String qwere = "INSERT INTO players(name,score) VALUES(?,?)";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(qwere);
            prSt.setString(1, name);
            prSt.setInt(2, score);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }//UpdatePlayer
    public void UpdatePlayer(String name, Integer score){
        String qwerea = "UPDATE players SET score=? WHERE name = ?";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(qwerea);
            prSt.setInt(1, score);
            prSt.setString(2, name);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean AlreadyExists(String name){
        String s = "SELECT 1\n" +
                "FROM players\n" +
                "WHERE name = ?;";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(s);
            prSt.setString(1, name);
            ResultSet resSet = prSt.executeQuery();
            return resSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public String GetFive(){
        String a = "SELECT * FROM players\n" +
                "ORDER BY score DESC LIMIT 5;";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(a);
            ResultSet resSet = prSt.executeQuery();
            Gson gson = new Gson();
            Map<Integer, Map<String, String>> tojson = new LinkedHashMap<>();
            Integer counter = 1;
            while (resSet.next()){
                Map<String, String> ply = new LinkedHashMap<>();
                ply.put("name", resSet.getString("name"));
                ply.put("score", resSet.getString("score"));
                tojson.put(counter, ply);
                counter++;
            }
            return gson.toJson(tojson);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
