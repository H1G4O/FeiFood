/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dao.Conexao;
import dao.PedidoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Pedido;
import model.ItemPedido;    
/**
 *
 * @author Higor
 */
public class ControlePedidoDAO {
    public int salvarPedido(Pedido pedido) {

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            PedidoDAO dao = new PedidoDAO(conn);

            // 1️⃣ SALVA O PEDIDO
            int idGerado = dao.inserirPedido(pedido);

            if (idGerado == -1) {
                JOptionPane.showMessageDialog(null, "Erro ao criar pedido!");
                return -1;
            }

            pedido.setIdPedido(idGerado);

            // 2️⃣ SALVA ITEMS
            for (ItemPedido item : pedido.getItens()) {
                dao.inserirItem(idGerado, item);
            }

            JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");
            return idGerado;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no banco: " + e.getMessage());
            return -1;
        }
    }

    public void excluirPedido(int id) {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            PedidoDAO dao = new PedidoDAO(conn);
            dao.deletarPedido(id);
            JOptionPane.showMessageDialog(null, "Pedido excluído!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
    }
}
