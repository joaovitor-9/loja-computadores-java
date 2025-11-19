package Singleton;
import AbstractFactory.*;
import Composite.Composite;
import Composite.Peca;
import java.util.HashMap;
import java.util.Map;

public class CatalogoPcsBases {
    private static Map<String, ComponenteInterface> pcsBaseProntos;

    public CatalogoPcsBases() {
        pcsBaseProntos = new HashMap<>();

        Composite pcBase_i3 = new Composite("PC básico Intel i3");
        pcBase_i3.adicionar(new Peca("Intel core i3 14100F", FabricaIntel.PRECO_I3));
        pcBase_i3.adicionar(new Peca("Placa mãe H610", FabricaIntel.PRECO_PLACA_H610));
        pcsBaseProntos.put(pcBase_i3.getNome(), pcBase_i3);

        Composite pcBase_i5 = new Composite("PC intermediário Intel i5");
        pcBase_i5.adicionar(new Peca("Intel core i5 14600K", FabricaIntel.PRECO_I5));
        pcBase_i5.adicionar(new Peca("Placa mãe B760", FabricaIntel.PRECO_PLACA_B760));
        pcsBaseProntos.put(pcBase_i5.getNome(), pcBase_i5);

        Composite pcBase_i7 = new Composite("PC avançado Intel i7");
        pcBase_i7.adicionar(new Peca("Intel core i7 14700K", FabricaIntel.PRECO_I7));
        pcBase_i7.adicionar(new Peca("Placa mãe B760", FabricaIntel.PRECO_PLACA_B760));
        pcsBaseProntos.put(pcBase_i7.getNome(), pcBase_i7);

        Composite pcBase_i9 = new Composite("PC muito avançado Intel i9");
        pcBase_i9.adicionar(new Peca("Intel core i9 14900K", FabricaIntel.PRECO_I9));
        pcBase_i9.adicionar(new Peca("Placa mãe Z790", FabricaIntel.PRECO_PLACA_Z790));
        pcsBaseProntos.put(pcBase_i9.getNome(), pcBase_i9);

        Composite pcBase_r3 = new Composite("PC básico Ryzen 3");
        pcBase_r3.adicionar(new Peca("AMD Ryzen 3 8300G", FabricaAmd.PRECO_RYZEN_3));
        pcBase_r3.adicionar(new Peca("Placa mãe A620", FabricaAmd.PRECO_PLACA_A620));
        pcsBaseProntos.put(pcBase_r3.getNome(), pcBase_r3);

        Composite pcBase_r5 = new Composite("PC intermediário Ryzen 5");
        pcBase_r5.adicionar(new Peca("AMD Ryzen 5 7600X", FabricaAmd.PRECO_RYZEN_5));
        pcBase_r5.adicionar(new Peca("Placa mãe B650", FabricaAmd.PRECO_PLACA_B650));
        pcsBaseProntos.put(pcBase_r5.getNome(), pcBase_r5);

        Composite pcBase_r7 = new Composite("PC avançado Ryzen 7");
        pcBase_r7.adicionar(new Peca("AMD Ryzen 7 7700X", FabricaAmd.PRECO_RYZEN_7));
        pcBase_r7.adicionar(new Peca("Placa mãe B650", FabricaAmd.PRECO_PLACA_B650));
        pcsBaseProntos.put(pcBase_r7.getNome(), pcBase_r7);

        Composite pcBase_r9 = new Composite("PC muito avançado Ryzen 9");
        pcBase_r9.adicionar(new Peca("AMD Ryzen 9 7900X", FabricaAmd.PRECO_RYZEN_9));
        pcBase_r9.adicionar(new Peca("Placa mãe X670", FabricaAmd.PRECO_PLACA_X670));
        pcsBaseProntos.put(pcBase_r9.getNome(), pcBase_r9);
    }
    public ComponenteInterface getPcPronto(String nome) {
        return pcsBaseProntos.get(nome);
    }
    public boolean ehPcPronto(String nome) {
        return pcsBaseProntos.containsKey(nome);
    }
    public Map<String, ComponenteInterface> getTodosPcs() {
        return pcsBaseProntos;
    }
}
