/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ClienteDAO;
import dao.Conexao;
import model.User;
import view.Cadastro;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Higor
 */
public class ControleCadastro {
    private Cadastro tela3;
    
    public ControleCadastro(Cadastro tela3){
        this.tela3=tela3;
    }
    
    public void SalvarUser(){
        String nome = tela3.getTxtNome().getText();
        String sobrenome = tela3.getTxtSobrenome().getText();
        String nasc = tela3.getTxtNascimento().getText();
        String email = tela3.getTxtEmailCadastro().getText();
        String senha = tela3.getTxtSenhaCadastro().getText();
        
        User user = new User(nome, sobrenome, nasc, email, senha);
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            dao.inserir(user);
            JOptionPane.showMessageDialog(tela3, "Usuário cadastrado!","Aviso!",
                                                JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(tela3, "Usuário não cadastrado!", "Erro", 
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
}
    

