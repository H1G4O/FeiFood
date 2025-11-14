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
    
     public void carregarTodos() {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ResultSet res = dao.pesquisar(""); 

            tela2.limparTabela();

            while (res.next()) {
                tela2.adicionarNaTabela(new Object[]{
                    res.getString("nome_alimento"),
                    res.getString("tipo_alimento"),
                    res.getDouble("preco_alimento"),
                    res.getString("descricao_alimento"),
                    res.getInt("porcao_alimento"),
                    res.getDouble("nota_alimento")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela2,
                "Erro ao carregar alimentos.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void pesquisa(){
        String nomePesquisa = tela2.getTxtPesquisa().getText();
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ResultSet res = dao.pesquisar(nomePesquisa);

            tela2.limparTabela();

            boolean achou = false;

            while (res.next()) {
                achou = true;

                tela2.adicionarNaTabela(new Object[]{
                    res.getString("nome_alimento"),
                    res.getString("tipo_alimento"),
                    res.getDouble("preco_alimento"),
                    res.getString("descricao_alimento"),
                    res.getInt("porcao_alimento"),
                    res.getDouble("nota_alimento")
                });
            }

            if (!achou) {
                JOptionPane.showMessageDialog(tela2,
                    "Nenhum alimento encontrado para: " + nomePesquisa,
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela2,
                "Erro ao pesquisar alimentos.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}