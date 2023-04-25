/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HLN.EPrice.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.HLN.EPrice.entities.EPrice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Henri
 */
public class Reader {
     
      private static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotterbot",
                    "librarian", "test123");
            return con;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
        
    public static void readFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    List<EPrice> epriceList = objectMapper.readValue(
            new File("C:\\javatraining\\SpotterBot\\projektiHenri\\ePrice\\src\\main\\java\\com\\HLN\\EPrice\\controllers\\epricesFinland.json"),
            new TypeReference<List<EPrice>>(){});
    for(EPrice ep : epriceList){
                create(ep);
            }
        epriceList.forEach(p -> System.out.println(p.toString()));
    }
   
      
    static EPrice create(EPrice e){
        try{
            Connection con = getConnection();
            String sql = "INSERT INTO price values(null,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stm.setDouble(1, e.getPrice());
            stm.setInt(2, e.getMonth());
            stm.setInt(3, e.getYear());
            int rowsAffected = stm.executeUpdate(); 
            ResultSet keys=stm.getGeneratedKeys();
            if (keys.next()) {
                int id=keys.getInt(1);
                stm.close();
                keys.close();
                con.close();
                return e;
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
