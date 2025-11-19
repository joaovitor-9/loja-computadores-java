package Singleton;
import java.util.Map;

public class Estoque {
    private Map<String, Integer> estoqueProdutos;
    public Estoque(Map<String, Integer> dadosIniciais) {
        this.estoqueProdutos = dadosIniciais;
    }

    public boolean baixarEstoque(String item) {
        int atual = getEstoque(item);
        if (atual > 0) {
            estoqueProdutos.put(item, atual - 1);
            return true;
        } else {
            System.out.println("Erro na baixa de " + item + " . Estoque zerado.");
            return false;
        }
    }

    public void adicionarEstoque(String item, int quantidade) {
        int atual = getEstoque(item);
        estoqueProdutos.put(item, atual + quantidade);
        System.out.println("Estoque de " + item + " atualizado" );
    }

    public int getEstoque(String item) {
        return estoqueProdutos.getOrDefault(item, 0);
    }

    public Map<String, Integer> getEstoqueCompleto() {
        return this.estoqueProdutos;
    }

    public void exibirEstoque() {
        System.out.println("\n--- 📈 RELATÓRIO DE ESTOQUE ---");
        if (estoqueProdutos.isEmpty()) {
            System.out.println("Estoque está vazio.");
            return;
        }
        for (Map.Entry<String, Integer> entry : estoqueProdutos.entrySet()) {
            System.out.println("Item: " + entry.getKey() + " | Quantidade: " + entry.getValue());
        }
        System.out.println("-------------------------------------------");
    }
}