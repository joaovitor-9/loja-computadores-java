package AbstractFactory;

public class PlacaMaeAmd implements PlacaMaeInterface {
    private String nome;
    private double preco;

    public PlacaMaeAmd(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    @Override
    public String getNome(){
        return this.nome;
    }
    @Override
    public double getPreco(){
        return this.preco;
    }
    @Override
    public String getDescricao(){
        return this.nome;
    }
    @Override
    public boolean compativel(ProcessadorInterface p) {
        return p instanceof ProcessadorAmd;
    }
}