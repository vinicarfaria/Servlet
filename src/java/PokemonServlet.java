/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pokemon;
import util.ConBD;
import com.google.gson.Gson;
import java.sql.PreparedStatement;

/**
 *
 * @author Vinicius
 */
@WebServlet(name = "PokemonServlet", urlPatterns = {"/PokemonServlet"})
public class PokemonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

                Connection con = ConBD.getConnection();

                if (con == null) {
                    throw new SQLException("Erro conectando");
                }
                Gson gson = new Gson();
                Statement stm = con.createStatement();
                
                //request.getparameter não pega os parametros
                Pokemon pokemon = new Pokemon();
                pokemon.setNome(gson.toJson(request.getParameter("txt-nome")));
                pokemon.setCp(request.getParameter("txt-cp"));
                pokemon.setGen(request.getParameter("txt-gen"));
                pokemon.setCandy(request.getParameter("txt-candy"));
                out.println(pokemon.getNome());
                //String nome = request.getParameter("txt-nome");
                //String cp = request.getParameter("txt-cp");
                //String gen = request.getParameter("txt-gen");
                //String candy = request.getParameter("txt-candy");
                
                //String insert = "INSERT INTO pokemon (nome,cp,gen,candy) "+" VALUES('"+ request.getParameter("txt-nome")+"','"+request.getParameter("txt-cp")+"','"+request.getParameter("txt-gen")+"','"+request.getParameter("txt-candy")+"');";
                String insert = "INSERT INTO pokemon (nome,cp,gen,candy)" + 
                                "VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(insert);
                preparedStatement.setString(1, pokemon.getNome());
                preparedStatement.setString(2, pokemon.getCp());
                preparedStatement.setString(3, pokemon.getGen());
                preparedStatement.setString(4, pokemon.getCandy());
                preparedStatement.executeUpdate();
                
                String sql = "SELECT * FROM pokemon";

                ResultSet rs = stm.executeQuery(sql);  
                
            
            

            

            ArrayList<Pokemon> listaPokemons = new ArrayList<Pokemon>();

            while (rs.next()) {
                Pokemon a = new Pokemon();
                a.setNome(rs.getString("nome"));
                a.setCp(rs.getString("cp"));
                a.setGen(rs.getString("gen"));
                a.setCandy(rs.getString("candy"));
                listaPokemons.add(a);
            }

            response.setContentType("application/json;charset=UTF-8");


            out.println(gson.toJson(listaPokemons));

                
        
        } catch (SQLException ex) {

        } catch (Exception e) {

        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
