package Composite;

import AbstractFactory.ComponenteInterface;
import java.util.ArrayList;
import java.util.List;

public class Composite implements ComponenteInterface {
    protected String nome;
    protected List<ComponenteInterface> componentes = new ArrayList<>();

    public Composite(String nome){
        this.nome = nome;
    }
    public void adicionar(ComponenteInterface componente){
        componentes.add(componente);
    }
    public void remover(ComponenteInterface componente){
        componentes.remove(componente);
    }
    public String getNome(){
        return nome;
    }
    public double getPreco(){
        double precoTotal = 0;
        for (ComponenteInterface c : componentes) {
            precoTotal += c.getPreco();
        }
        return precoTotal;
    }
    public String getDescricao(){
        StringBuilder desc = new StringBuilder(nome + " contendo:\n");
        for (ComponenteInterface c : componentes) {
            desc.append("\t- ").append(c.getDescricao()).append("\n");
        }
        return desc.toString();
    }
}
