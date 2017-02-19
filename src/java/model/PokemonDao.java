/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import util.ConBD;

/**
 *
 * @author Vinicius
 */
public class PokemonDao {
    public boolean cadastrarNovoPokemon(Pokemon pokemon) {
        
        Statement stm;
        
        try {
            
            Connection con = ConBD.getConnection();
            
            stm = con.createStatement();
            
            if (con==null) {
                throw new SQLException("Erro conectando");
            }

            String sql = "INSERT INTO pokemon (nome,cp,gen,candy)"
                    + "VALUES ('"+pokemon.getNome()+"','"+pokemon.getCp()+"',"
                    + "'"+pokemon.getGen()+"',"
                    + "'"+pokemon.getCandy()+"')";
            
            stm.executeUpdate(sql);
            
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        
        return true;
        
    }
}
