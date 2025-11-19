package Singleton;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RepositorioEstoque {
    private static final String ARQUIVO_ESTOQUE = "estoque.ser";

    public Map<String, Integer> carregarEstoque() {
        Map<String, Integer> estoqueCarregado = null;
        File arquivo = new File(ARQUIVO_ESTOQUE);
        if (arquivo.exists() && arquivo.length() > 0) {
            try (FileInputStream fis = new FileInputStream(arquivo);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                estoqueCarregado = (Map<String, Integer>) ois.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao ler o arquivo de estoque: " + e.getMessage());
                estoqueCarregado = criarEstoquePadrao();
                salvarEstoque(estoqueCarregado);
            }
        } else {
            estoqueCarregado = criarEstoquePadrao();
            salvarEstoque(estoqueCarregado);
        }
        return estoqueCarregado;
    }
    public void salvarEstoque(Map<String, Integer> estoqueParaSalvar) {
        try (FileOutputStream fos = new FileOutputStream(ARQUIVO_ESTOQUE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(estoqueParaSalvar);
        } catch (IOException e) {
            System.err.println("Erro ao salvar estoque no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private Map<String, Integer> criarEstoquePadrao() {
        Map<String, Integer> mapaPadrao = new HashMap<>();

        mapaPadrao.put("PC básico Intel i3", 5);
        mapaPadrao.put("PC intermediário Intel i5", 8);
        mapaPadrao.put("PC avançado Intel i7", 7);
        mapaPadrao.put("PC muito avançado Intel i9", 3);

        mapaPadrao.put("PC básico Ryzen 3", 4);
        mapaPadrao.put("PC intermediário Ryzen 5", 10);
        mapaPadrao.put("PC avançado Ryzen 7", 5);
        mapaPadrao.put("PC muito avançado Ryzen 9", 2);

        return mapaPadrao;
    }
}