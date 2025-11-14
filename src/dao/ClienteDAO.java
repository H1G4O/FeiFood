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
    
    public void inserir(User user)throws SQLException{
        String sql = "insert into tb_cliente(nome_cliente, "
                + "sobrenome_cliente, nascimento_cliente, email_cliente, "
                + "senha_cliente)values('"+user.getNome()+"', '"
                +user.getSobrenome()+"', '"
                +user.getNasc()+"', '"
                +user.getEmail()+"', '"
                +user.getSenha()+"')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
    }
}
