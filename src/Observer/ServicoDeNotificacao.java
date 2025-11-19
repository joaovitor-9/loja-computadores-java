package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoDeNotificacao {
    private Map<String, List<ObserverInterface>> inscritos;

    public ServicoDeNotificacao() {
        this.inscritos = new HashMap<>();
    }
    public void inscrever(String nomeProduto, ObserverInterface observer) {
        List<ObserverInterface> lista = inscritos.computeIfAbsent(nomeProduto, k -> new ArrayList<>());
        lista.add(observer);
        System.out.println("Cliente inscrito com sucesso para " + nomeProduto);
    }
    public void notificarPorProduto(String nomeProduto) {
        List<ObserverInterface> lista = inscritos.get(nomeProduto);
        if (lista == null || lista.isEmpty()) {
            return;
        }
        for (ObserverInterface observer : new ArrayList<>(lista)) {
            observer.notificar(nomeProduto);
        }
        lista.clear();
    }
}