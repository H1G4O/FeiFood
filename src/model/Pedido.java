/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Higor
 */
public class Pedido {
    private LocalDate dataPedido;
    private LocalTime horaPedido;
    private int idCliente, idPedido;
    private double valorPedido; // total
    private List<ItemPedido> itens;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public Pedido() {
        this.itens = new ArrayList<>();
        this.dataPedido = LocalDate.now();
        this.horaPedido = LocalTime.now();
    }

    public Pedido(int idCliente) {
        this();
        this.idCliente = idCliente;
    }

    public LocalDate getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDate dataPedido) { this.dataPedido = dataPedido; }

    public LocalTime getHoraPedido() { return horaPedido; }
    public void setHoraPedido(LocalTime horaPedido) { this.horaPedido = horaPedido; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public double getValorPedido() {
        recalcValor();
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) { this.valorPedido = valorPedido; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; recalcValor(); }

    public void adicionarItem(ItemPedido item) {
        // se já existir o mesmo idAlimento, soma a quantidade
        for (ItemPedido ip : itens) {
            if (ip.getIdAlimento() == item.getIdAlimento()) {
                ip.setQuantidade(ip.getQuantidade() + item.getQuantidade());
                recalcValor();
                return;
            }
        }
        itens.add(item);
        recalcValor();
    }

    public void removerItem(int idAlimento) {
        itens.removeIf(i -> i.getIdAlimento() == idAlimento);
        recalcValor();
    }

    public void atualizarQuantidade(int idAlimento, int novaQtd) {
        for (ItemPedido ip : itens) {
            if (ip.getIdAlimento() == idAlimento) {
                ip.setQuantidade(novaQtd);
                break;
            }
        }
        recalcValor();
    }

    private void recalcValor() {
        double total = 0.0;
        for (ItemPedido ip : itens) total += ip.getSubtotal();
        this.valorPedido = total;
    }
}
