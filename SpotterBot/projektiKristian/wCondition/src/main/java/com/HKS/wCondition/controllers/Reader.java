
package com.HKS.wCondition.controllers;

import com.HKS.wCondition.Entities.Weather;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author boman
 */
public class Reader {
    
    private static Connection getConnection(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/spotterbot",
                                            "librarian","test123");
            return con;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void readFile() throws IOException{
    ObjectMapper objectMapper = new ObjectMapper();
    List<Weather> weatherList = objectMapper.readValue(
            new File("C:\\javatraining\\SpotterBot\\projektiKristian\\wCondition\\src\\main\\java\\com\\HKS\\wCondition\\controllers\\weatherFinland.json"),
            new TypeReference<List<Weather>>(){});
    for(Weather w: weatherList){
                create(w);
            }
//        weatherList.forEach(x -> System.out.println(x.toString()));
    }
    
    
    
    static Weather create(Weather b) {
        try{
            Connection con=getConnection();
            String sql="INSERT INTO weather values(null,?,?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stm.setDouble(1, b.getTemperature());
            stm.setDouble(2, b.getWind());
            stm.setDouble(3, b.getRain());
            stm.setInt(4, b.getMonth());
            stm.setInt(5, b.getYear());
            int rowsAffected=stm.executeUpdate(); //???
            ResultSet keys=stm.getGeneratedKeys();
            if (keys.next()) {
                int id=keys.getInt(1);
                stm.close();
                keys.close();
                con.close();
                return b;
            }
            
            stm.close();
            con.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
  
}
