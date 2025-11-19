package Singleton;

import AbstractFactory.ComponenteInterface;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioVendas {
    private static final String ARQUIVO_VENDAS = "relatorioVendas.txt";
    public void registrarVenda(ComponenteInterface vendido) {
        try (FileWriter fw = new FileWriter(ARQUIVO_VENDAS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            pw.println("VENDA REGISTRADA EM " + LocalDateTime.now().format(dtf));
            pw.println("ITEM: " + vendido.getNome());
            pw.println("VALOR TOTAL: R$ " + vendido.getPreco());
            pw.println("DETALHES:");
            pw.println(vendido.getDescricao());
            pw.println("-------------------------------------------------\n");

        } catch (IOException e) {
            System.err.println("Erro ao registrar venda no arquivo: " + e.getMessage());
        }
    }
    public void exibirRelatorioVendas() {
        System.out.println("RELATÓRIO DE VENDAS");
        try (BufferedReader ler = new BufferedReader(new FileReader(ARQUIVO_VENDAS))) {
            String linha;
            while ((linha = ler.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum relatório de vendas encontrado. Venda algo primeiro.");
        } catch (IOException e) {
            System.err.println("Erro ao ler relatório de vendas: " + e.getMessage());
        }
        System.out.println("-------------------------------------------");
    }
}
