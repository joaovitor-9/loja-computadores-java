package AbstractFactory;

import java.util.List;

public interface FabricaDeComponentesInterface {
    ProcessadorInterface criarProcessador(String modelo);
    PlacaMaeInterface criarPlacaMae(String modelo);

    List<String> getModelosProcessador();
    List<String> getModelosPlacaMae();
}