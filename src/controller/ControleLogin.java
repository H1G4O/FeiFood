/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ClienteDAO;
import dao.Conexao;
import model.User;
import view.TelaInicial;
import view.TelaPrincipal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Higor
 */
public class ControleLogin {
    private TelaInicial tela1;

    public ControleLogin(TelaInicial tela1) {
        this.tela1 = tela1;
    }
    
    public void loginCliente(){
        
        User user = new User(null, null, null, tela1.getTxtEmail().getText(), 
                tela1.getTxtSenha().getText());
        
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            ResultSet res = dao.consultar(user);
            
            if(res.next()){
                JOptionPane.showMessageDialog(tela1, "Login Efetuado","Aviso!",
                                                JOptionPane.INFORMATION_MESSAGE);
                String nome = res.getString("nome_cliente");
                String sobrenome = res.getString("sobrenome_cliente");
                String nasc = res.getString("nascimento_cliente");
                String email = res.getString("email_cliente");
                String senha = res.getString("senha_cliente");
                
                TelaPrincipal logado = new TelaPrincipal(new User(nome, sobrenome, nasc, email, senha));
                
                logado.setVisible(true);
                tela1.setVisible(false);
                
            }else{
                JOptionPane.showMessageDialog(tela1, "Login não efetuado", "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(tela1, "Erro de conexão", "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
}