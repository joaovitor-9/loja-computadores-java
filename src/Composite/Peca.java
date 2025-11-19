package Composite;

import AbstractFactory.ComponenteInterface;

public class Peca implements ComponenteInterface {
    protected String nome;
    protected double preco;

    public Peca(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public String getDescricao() {
        return getNome();
    }
}

