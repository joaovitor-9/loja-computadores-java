package Singleton;
import AbstractFactory.ComponenteInterface;
import java.util.Map;

public class GerenciadorDeDados {
    private static GerenciadorDeDados instancia;

    public static synchronized GerenciadorDeDados getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDeDados();
        }
        return instancia;
    }
    private final Estoque estoque;
    private final RepositorioEstoque repositorioEstoque;
    private final RelatorioVendas relatorioVendas;
    private final CatalogoPcsBases catalogo;

    private GerenciadorDeDados() {
        this.repositorioEstoque = new RepositorioEstoque();
        Map<String, Integer> dadosIniciais = repositorioEstoque.carregarEstoque();
        this.estoque = new Estoque(dadosIniciais);
        this.relatorioVendas = new RelatorioVendas();
        this.catalogo = new CatalogoPcsBases();
    }
    public void registrarVenda(ComponenteInterface vendido) {
        if (catalogo.ehPcPronto(vendido.getNome())) {
            relatorioVendas.registrarVenda(vendido);
            estoque.baixarEstoque(vendido.getNome());
        } else {
            System.out.println("LOG: Registrando venda de item customizado (sem baixa de estoque).");
            relatorioVendas.registrarVenda(vendido);
        }
    }
    public void registrarVenda(ComponenteInterface vendido, String nomeDoItemNoEstoque) {
        boolean baixaNoEstoque = estoque.baixarEstoque(nomeDoItemNoEstoque);
        if (baixaNoEstoque) {
            repositorioEstoque.salvarEstoque(estoque.getEstoqueCompleto());
            relatorioVendas.registrarVenda(vendido);
            System.out.println("Venda Realizada e Salva com Sucesso!");
        } else {
            System.out.println("ERRO: Venda não realizada, estoque insuficiente.");
        }
    }
    public void adicionarEstoque(String item, int quantidade) {
        estoque.adicionarEstoque(item, quantidade);

        repositorioEstoque.salvarEstoque(estoque.getEstoqueCompleto());
    }
    public ComponenteInterface getPcProntoDoEstoque(String nome) {
        if (estoque.getEstoque(nome) > 0) {
            return catalogo.getPcPronto(nome);
        }
        else{
            System.out.println(nome + " está fora de estoque.");
        }
        return null;
    }
    public void exibirEstoque() {
        estoque.exibirEstoque();
    }
    public void exibirRelatorioVendas() {
        relatorioVendas.exibirRelatorioVendas();
    }
    public Map<String, ComponenteInterface> getCatalogoMap() {
        return catalogo.getTodosPcs();
    }
    public int consultarQuantidadeAtual(String nome) {
        return estoque.getEstoque(nome);
    }
}
