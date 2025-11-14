/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AlimentoDAO;
import dao.ClienteDAO;
import dao.Conexao;
import dao.ControlePedidoDAO;
import model.Pedido;
import model.ItemPedido;
import View.RealizarPedido;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Higor
 */
public class ControleRealizarPedido {
    private RealizarPedido tela;
    private Pedido pedido;
    private String emailCliente;
    private int idCliente;

    public ControleRealizarPedido(RealizarPedido tela, String emailCliente) {
        this.tela = tela;
        this.emailCliente = emailCliente;
        this.pedido = new Pedido();
        buscarIdCliente();
    }
    
    // Buscar ID do cliente no banco
    private void buscarIdCliente() {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            this.idCliente = dao.buscarIdPorEmail(emailCliente);
            pedido.setIdCliente(idCliente);
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao buscar cliente: " + e.getMessage());
        }
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    // 1️⃣ Adicionar item ao pedido
    public void adicionarItem() {
        int linha = tela.getTbAlimentos().getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(tela, "Selecione um alimento!");
            return;
        }

        // PEGAR DADOS DA TABELA
        int idAlimento = (int) tela.getTbAlimentos().getValueAt(linha, 0);
        String nome = tela.getTbAlimentos().getValueAt(linha, 1).toString();
        double preco = (double) tela.getTbAlimentos().getValueAt(linha, 3);

        // QUANTIDADE DO COMBOBOX
        int quantidade = Integer.parseInt(tela.getCbxQtd().getSelectedItem().toString());

        ItemPedido item = new ItemPedido(idAlimento, nome, preco, quantidade);
        pedido.adicionarItem(item);

        atualizarCarrinho();
        JOptionPane.showMessageDialog(tela, "Item adicionado ao carrinho!");
    }

    // 2️⃣ Remover item do pedido
    // 2️⃣ Remover item do pedido
    public void removerItem() {
        int linha = tela.getTbCarrinho().getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(tela, "Selecione um item do carrinho!");
            return;
        }

        // Verifica se a célula não é null antes de chamar toString()
        Object nomeObj = tela.getTbCarrinho().getValueAt(linha, 0);

        if (nomeObj == null) {
            JOptionPane.showMessageDialog(tela, "Erro ao identificar o item!");
            return;
        }

        String nomeAlimento = nomeObj.toString();

        // Buscar o ID do alimento pelo nome
        for (ItemPedido item : pedido.getItens()) {
            if (item.getNomeAlimento().equals(nomeAlimento)) {
                pedido.removerItem(item.getIdAlimento());
                break;
            }
        }

        atualizarCarrinho();
        JOptionPane.showMessageDialog(tela, "Item removido do carrinho!");
    }
    
    // 3️⃣ Atualizar tabela do carrinho
    public void atualizarCarrinho() {
        DefaultTableModel modelo = (DefaultTableModel) tela.getTbCarrinho().getModel();
        modelo.setRowCount(0);
        
        for (ItemPedido item : pedido.getItens()) {
            modelo.addRow(new Object[]{
                item.getNomeAlimento(),
                item.getQuantidade(),
                String.format("R$ %.2f", item.getPrecoUnitario()),
                String.format("R$ %.2f", item.getSubtotal())
            });
        }
        
        // Atualizar label do total
        tela.getLblTotal().setText(String.format("Total: R$ %.2f", pedido.getValorPedido()));
    }
    
    // 4️⃣ Finalizar e salvar pedido
    public void finalizarPedido() {
        if (pedido.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Adicione itens ao carrinho antes de finalizar!");
            return;
        }
        
        int confirma = JOptionPane.showConfirmDialog(tela, 
            "Confirmar pedido no valor de R$ " + String.format("%.2f", pedido.getValorPedido()) + "?",
            "Confirmar Pedido", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirma == JOptionPane.YES_OPTION) {
            ControlePedidoDAO controle = new ControlePedidoDAO();
            int idPedido = controle.salvarPedido(pedido);
            
            if (idPedido != -1) {
                // Limpar carrinho após salvar
                pedido = new Pedido();
                pedido.setIdCliente(idCliente);
                atualizarCarrinho();
            }
        }
    }
    
    // 5️⃣ Carregar alimentos na tabela
    public void carregarAlimentos() {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ResultSet res = dao.pesquisar("");
            
            DefaultTableModel modelo = (DefaultTableModel) tela.getTbAlimentos().getModel();
            modelo.setRowCount(0);
            
            while (res.next()) {
                modelo.addRow(new Object[]{
                    res.getInt("id_alimento"),
                    res.getString("nome_alimento"),
                    res.getString("tipo_alimento"),
                    res.getDouble("preco_alimento"),
                    res.getString("descricao_alimento"),
                    res.getInt("porcao_alimento"),
                    res.getDouble("nota_alimento")
                });
            }
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao carregar alimentos: " + e.getMessage());
        }
    }
    
    // 6️⃣ Pesquisar alimentos
    public void pesquisarAlimentos(String termo) {
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            AlimentoDAO dao = new AlimentoDAO(conn);
            ResultSet res = dao.pesquisar(termo);
            
            DefaultTableModel modelo = (DefaultTableModel) tela.getTbAlimentos().getModel();
            modelo.setRowCount(0);
            
            boolean encontrou = false;
            while (res.next()) {
                encontrou = true;
                modelo.addRow(new Object[]{
                    res.getInt("id_alimento"),
                    res.getString("nome_alimento"),
                    res.getString("tipo_alimento"),
                    res.getDouble("preco_alimento"),
                    res.getString("descricao_alimento"),
                    res.getInt("porcao_alimento"),
                    res.getDouble("nota_alimento")
                });
            }
            
            if (!encontrou) {
                JOptionPane.showMessageDialog(tela, "Nenhum alimento encontrado!");
            }
            
            conn.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(tela, "Erro ao pesquisar: " + e.getMessage());
        }
    }
}