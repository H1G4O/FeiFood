/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author Higor
 */
public class ClienteDAO {
    private Connection conn;
    
    public ClienteDAO(Connection conn){
        this.conn = conn;
    }
    
    public ResultSet consultar(User user)throws SQLException{
        String sql = "select * from tb_cliente where email_cliente = ? and senha_cliente = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getSenha());
        statement.execute();
        ResultSet resultado=statement.getResultSet();
        return resultado;
    }
    
    public void inserir(User user) throws SQLException {
        String sql = "INSERT INTO tb_cliente(nome_cliente, sobrenome_cliente, nascimento_cliente, email_cliente, senha_cliente) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getNome());
        statement.setString(2, user.getSobrenome());
        statement.setString(3, user.getNasc());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getSenha());
        statement.execute();
        conn.close();
    }
    public int buscarIdPorEmail(String email) throws SQLException {
        String sql = "SELECT id_cliente FROM tb_cliente WHERE email_cliente = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultado = statement.executeQuery();

        if (resultado.next()) {
            return resultado.getInt("id_cliente");
        }
        return -1;
    }
}
