/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Pedido;
import model.ItemPedido;

/**
 *
 * @author Higor
 */
public class PedidoDAO {
    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    // 1️⃣ Criar pedido
    public int inserirPedido(Pedido p) throws SQLException {
        String sql = "INSERT INTO tb_pedidos (data_pedido, hora_pedido, id_cliente, valor_pedido) "
                   + "VALUES (?, ?, ?, ?) RETURNING id_pedido";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, java.sql.Date.valueOf(p.getDataPedido()));
        stmt.setTime(2, java.sql.Time.valueOf(p.getHoraPedido()));
        stmt.setInt(3, p.getIdCliente());
        stmt.setDouble(4, p.getValorPedido());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_pedido");
        }

        return -1;
    }

    // 2️⃣ Inserir itens do pedido
// 2️⃣ Inserir itens do pedido
    public void inserirItem(int idPedido, ItemPedido item) throws SQLException {
        String sql = "INSERT INTO tb_pedido_alimento (id_pedido, id_alimento, qtd_pedido_alimento, total_item) "
                   + "VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPedido);
        stmt.setInt(2, item.getIdAlimento());
        stmt.setInt(3, item.getQuantidade());
        stmt.setDouble(4, item.getSubtotal());

        stmt.execute();
    }

    // 3️⃣ Excluir pedido (com CASCADE já funciona pros itens)
    public void deletarPedido(int idPedido) throws SQLException {
        String sql = "DELETE FROM tb_pedidos WHERE id_pedido = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPedido);
        stmt.execute();
    }
        // 4️⃣ Buscar todos os pedidos de um cliente
    public ResultSet buscarPedidosCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM tb_pedidos WHERE id_cliente = ? ORDER BY data_pedido DESC, hora_pedido DESC";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCliente);
        return stmt.executeQuery();
    }

    // 5️⃣ Buscar itens de um pedido específico
    public ResultSet buscarItensPedido(int idPedido) throws SQLException {
        String sql = "SELECT pa.*, a.nome_alimento, a.preco_alimento " +
                     "FROM tb_pedido_alimento pa " +
                     "JOIN tb_alimentos a ON pa.id_alimento = a.id_alimento " +
                     "WHERE pa.id_pedido = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPedido);
        return stmt.executeQuery();
    }

    // 6️⃣ Atualizar pedido
    public void atualizarPedido(Pedido p) throws SQLException {
        String sql = "UPDATE tb_pedidos SET data_pedido = ?, hora_pedido = ?, valor_pedido = ? WHERE id_pedido = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDate(1, java.sql.Date.valueOf(p.getDataPedido()));
        stmt.setTime(2, java.sql.Time.valueOf(p.getHoraPedido()));
        stmt.setDouble(3, p.getValorPedido());
        stmt.setInt(4, p.getIdPedido());
        stmt.execute();
    }

    // 7️⃣ Deletar itens de um pedido
    public void deletarItensPedido(int idPedido) throws SQLException {
        String sql = "DELETE FROM tb_pedido_alimento WHERE id_pedido = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idPedido);
        stmt.execute();
    }
}
