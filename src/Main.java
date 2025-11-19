import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import AbstractFactory.*;
import Composite.*;
import Decorator.*;
import Singleton.GerenciadorDeDados;
import Observer.Cliente;
import Observer.ServicoDeNotificacao;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GerenciadorDeDados gerenciador = GerenciadorDeDados.getInstancia();
    private static ServicoDeNotificacao notificacao = new ServicoDeNotificacao();

    public static void main(String[] args) {
        System.out.println("SISTEMA LOJA DE COMPUTADORES");
        while (true) {
            exibirMenuPrincipal();
             int escolha = lerEntradaInt();

            switch (escolha) {
                case 1:
                    menuVenderPc();
                    break;
                case 2:
                    menuAdicionarEstoque();
                    break;
                case 3:
                    gerenciador.exibirEstoque();
                    break;
                case 4:
                    gerenciador.exibirRelatorioVendas();
                    break;
                case 5:
                    menuNotificacao();
                    break;
                case 0:
                    System.out.println("A encerrar sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    private static int lerEntradaInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas números.");
                scanner.next();
                System.out.print("Tente novamente: ");
            }
        }
    }
    private static void exibirMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Vender PC");
        System.out.println("2. Adicionar PC no estoque");
        System.out.println("3. Exibir estoque");
        System.out.println("4. Exibir relatório de vendas");
        System.out.println("5. Gerenciar Lista de Espera (Avise-me)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuVenderPc() {
        System.out.println("\nMENU DE VENDAS");
        System.out.println("1. Vender PC Pronto (sem upgrade)");
        System.out.println("2. Vender PC pronto (com Upgrade)");
        System.out.println("3. Montar e Vender PC Customizado (com upgrade)");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");

        int escolha = lerEntradaInt();
        switch (escolha) {
            case 1:
                venderPcPronto();
                break;
            case 2:
                menuVendaComUpgrade();
                break;
            case 3:
                venderPcMontado();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }
    private static void venderPcPronto() {
        System.out.println("Venda de PC Pronto (Sem Upgrades)");
        ComponenteInterface pcEscolhido = escolherPcDoEstoque();

        if (pcEscolhido == null) {
            return;
        }
        gerenciador.registrarVenda(pcEscolhido);
    }
    private static void menuVendaComUpgrade() {
        System.out.println("Venda de PC do Estoque com Upgrades");
        ComponenteInterface pcBase = escolherPcDoEstoque();
        if (pcBase == null) {
            return;
        }
        System.out.println("\nPC selecionado: " + pcBase.getNome());
        System.out.println("Preço Base: R$ " + pcBase.getPreco());

        String nomeOriginalParaEstoque = pcBase.getNome();
        ComponenteInterface pcFinal = menuAplicarUpgrades(pcBase);
        gerenciador.registrarVenda(pcFinal, nomeOriginalParaEstoque);
    }
    private static ComponenteInterface menuAplicarUpgrades(ComponenteInterface computadorBase) {
        while (true) {
            System.out.println("\n--- Adicionar Upgrades ---");
            System.out.println("Preço atual: R$ " + computadorBase.getPreco());
            System.out.println("1. Adicionar Water Cooler (R$ 450.0)");
            System.out.println("2. Adicionar Kit RGB (R$ 200.0)");
            System.out.println("3. Adicionar Kit Fan (R$ 49.0)");
            System.out.println("0. Concluir Compra");
            System.out.print("Escolha um upgrade: ");

            int escolha = lerEntradaInt();

            switch (escolha) {
                case 1:
                    computadorBase = new WaterCoolerDecorator(computadorBase);
                    System.out.println("Water Cooler adicionado!");
                    break;
                case 2:
                    computadorBase = new RGBDecorator(computadorBase);
                    System.out.println("Kit RGB adicionado!");
                    break;
                case 3:
                    computadorBase = new KitFanDecorator(computadorBase);
                    System.out.println("Kit Fan adicionado!");
                    break;
                case 0:
                    return computadorBase;
                default:
                    System.out.println("Opção de upgrade inválida.");
            }
        }
    }
    private static void venderPcMontado() {
        System.out.println("Venda de PC Customizado");
        System.out.println("Escolha a plataforma: (1) Intel, (2) AMD");

        int plat = lerEntradaInt();

        FabricaDeComponentesInterface fabrica;

        if (plat == 1)
            fabrica = new FabricaIntel();
        else if (plat == 2)
            fabrica = new FabricaAmd();
        else { System.out.println("Opção inválida.");
            return;
        }

        ProcessadorInterface processador = escolherProcessador(fabrica);

        if (processador == null)
            return;

        PlacaMaeInterface placaMae = escolherPlacaMae(fabrica);

        if (placaMae == null)
            return;

        if (!placaMae.compativel(processador)) {
            System.out.println("ERRO: Peças incompatíveis! A montagem falhou.");
            return;
        }
        Composite pcMontado = new Composite("PC Customizado");
        pcMontado.adicionar(processador);
        pcMontado.adicionar(placaMae);
        pcMontado.adicionar(new Peca("16GB RAM", 300.0));
        pcMontado.adicionar(new Peca("SSD 1TB", 350.0));

        System.out.println("\n--- PC Base Montado ---");
        System.out.println(pcMontado.getDescricao());
        System.out.println("Preço Base: R$ " + pcMontado.getPreco());

        ComponenteInterface pcFinal = menuAplicarUpgrades(pcMontado);
        gerenciador.registrarVenda(pcFinal);
    }

    private static ProcessadorInterface escolherProcessador(FabricaDeComponentesInterface fabrica) {
        System.out.println("Escolha um Processador:");
        List<String> modelos = fabrica.getModelosProcessador();
        for (int i = 0; i < modelos.size(); i++) {
            System.out.println((i + 1) + ". " + modelos.get(i));
        }
        int escolha = lerEntradaInt() - 1;
        if (escolha < 0 || escolha >= modelos.size()) {
            System.out.println("Seleção inválida.");
            return null;
        }
        return fabrica.criarProcessador(modelos.get(escolha));
    }
    private static PlacaMaeInterface escolherPlacaMae(FabricaDeComponentesInterface fabrica) {
        System.out.println("Escolha uma Placa-mãe:");
        List<String> modelos = fabrica.getModelosPlacaMae();
        for (int i = 0; i < modelos.size(); i++) {
            System.out.println((i + 1) + ". " + modelos.get(i));
        }
        int escolha = lerEntradaInt() - 1;
        if (escolha < 0 || escolha >= modelos.size()) {
            System.out.println("Seleção inválida."); return null;
        }
        return fabrica.criarPlacaMae(modelos.get(escolha));
    }
    private static ComponenteInterface escolherPcDoEstoque() {
        Map<String, ComponenteInterface> pcs = gerenciador.getCatalogoMap();

        if (pcs == null || pcs.isEmpty()) {
            System.out.println("Nenhum PC pronto definido no catálogo.");
            return null;
        }

        List<String> chaves = new ArrayList<>(pcs.keySet());

        System.out.println("\n--- Escolha um PC ---");
        for (int i = 0; i < chaves.size(); i++) {
            String chave = chaves.get(i);
            ComponenteInterface pc = pcs.get(chave);
            int qtdEstoque = gerenciador.consultarQuantidadeAtual(chave);
            if (qtdEstoque > 0) {
                System.out.println((i + 1) + ". " + pc.getNome() + " (R$ " + pc.getPreco() + ")");
            }
        }
        System.out.println("0. Voltar ao menu");
        System.out.print("Escolha o PC: ");
        int escolha = lerEntradaInt();

        if (escolha == 0)
            return null;

        int indice = escolha - 1;

        if (indice < 0 || indice >= chaves.size()) {
            System.out.println("Seleção inválida.");
            return null;
        }
        String chaveEscolhida = chaves.get(indice);
        ComponenteInterface pcDoEstoque = gerenciador.getPcProntoDoEstoque(chaveEscolhida);

        if (pcDoEstoque != null) {
            return pcDoEstoque;
        } else {
            return null;
        }
    }
    private static void menuAdicionarEstoque() {
        System.out.println("\n--- 2. Adicionar Estoque ---");
        gerenciador.exibirEstoque();
        System.out.println("NOTA: Use nomes exatos como 'PC Básico Intel i3' ou 'RTX_4090'");
        System.out.print("Digite o código do item para reabastecer: ");
        scanner.nextLine();
        String item = scanner.nextLine();

        System.out.print("Digite a quantidade a adicionar: ");
        int quantidade = lerEntradaInt();
        if (quantidade > 0) {
            gerenciador.adicionarEstoque(item, quantidade);
            System.out.println("A verificar lista de espera para '" + item + "'...");
            notificacao.notificarPorProduto(item);
        } else {
            System.out.println("Quantidade deve ser positiva.");
        }
    }
    private static void menuNotificacao() {
        System.out.println("Gerenciar Lista de Espera (Avise-me)");
        System.out.print("Digite o nome do Cliente: ");
        scanner.nextLine();
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente(nome);
        System.out.print("Qual item o cliente deseja ser notificado? (Nome exato): ");
        String item = scanner.nextLine();

        notificacao.inscrever(item, cliente);
    }
}