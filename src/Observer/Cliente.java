package Observer;

public class Cliente implements ObserverInterface {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }
    @Override
    public void notificar(String nomeProduto) {
        System.out.println("ALERTA para o cliente: " + this.nome);
        System.out.println("O produto '" + nomeProduto + "' está de volta ao estoque!");
    }
    public String getNome() {
        return nome;
    }
}
