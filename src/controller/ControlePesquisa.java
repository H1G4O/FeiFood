/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.AlimentoDAO;
import dao.Conexao;
import model.Alimento;
import view.TelaPrincipal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Higor
 */
public class ControlePesquisa {
    private TelaPrincipal tela2;
    
    public ControlePesquisa(TelaPrincipal tela2){
        this.tela2=tela2;
    }
    
    public void pesquisa(){
        String nomePesquisa = tela2.getTxtPesquisa().getText();
        Conexao conexao = new Conexao();
        
        try{
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ResultSet res = dao.consultar(nomePesquisa);
            
        }catch{
            
        }
        
        
    }
    
}
