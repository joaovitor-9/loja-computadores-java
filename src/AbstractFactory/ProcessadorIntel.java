package AbstractFactory;

public class ProcessadorIntel implements ProcessadorInterface {
    private String nome;
    private double preco;

    public ProcessadorIntel(String nome, double preco) {
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
        return this.nome; // Continua simples, ótimo
    }
}