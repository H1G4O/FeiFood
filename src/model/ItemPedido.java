/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Higor
 */
public class ItemPedido {
    private int idAlimento;   
    private String nomeAlimento;
    private double precoUnitario;
    private int quantidade;

    public ItemPedido() {}

    public ItemPedido(int idAlimento, String nomeAlimento, double precoUnitario, int quantidade) {
        this.idAlimento = idAlimento;
        this.nomeAlimento = nomeAlimento;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public int getIdAlimento() { return idAlimento; }
    public void setIdAlimento(int idAlimento) { this.idAlimento = idAlimento; }

    public String getNomeAlimento() { return nomeAlimento; }
    public void setNomeAlimento(String nomeAlimento) { this.nomeAlimento = nomeAlimento; }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getSubtotal() {
        return precoUnitario * quantidade;
    }
}
