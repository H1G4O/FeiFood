/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Alimento;

/**
 *
 * @author Higor
 */
public class AlimentoDAO {
    private Connection conn;
    
    public AlimentoDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet pesquisar(String nome) throws SQLException {
        String sql = "SELECT * FROM tb_alimentos WHERE nome_alimento ILIKE ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + nome + "%"); // pesquisa parcial
        statement.execute();
        ResultSet resultado = statement.getResultSet();
    return resultado;
}
    
}
