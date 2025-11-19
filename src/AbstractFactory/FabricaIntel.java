package AbstractFactory;

import java.util.List;

public class FabricaIntel implements FabricaDeComponentesInterface {
    public static final double PRECO_I3 = 850.0;
    public static final double PRECO_I5 = 1600.0;
    public static final double PRECO_I7 = 3000.0;
    public static final double PRECO_I9 = 4200.0;

    public static final double PRECO_PLACA_H610 = 700.0;
    public static final double PRECO_PLACA_B760 = 1200.0;
    public static final double PRECO_PLACA_Z790 = 2800.0;

    @Override
    public ProcessadorInterface criarProcessador(String modelo) {
        return switch (modelo) {
            case "i3" -> new ProcessadorIntel("Intel Core i3 14100F", PRECO_I3);
            case "i5" -> new ProcessadorIntel("Intel Core i5 14600K", PRECO_I5);
            case "i7" -> new ProcessadorIntel("Intel Core i7 14700K", PRECO_I7);
            case "i9" -> new ProcessadorIntel("Intel Core i9 14900K", PRECO_I9);
            default -> throw new IllegalArgumentException("Modelo de processador Intel desconhecido: " + modelo);
        };
    }
    @Override
    public PlacaMaeInterface criarPlacaMae(String modelo) {
        return switch (modelo) {
            case "H610" -> new PlacaMaeIntel("Placa-mãe H610", PRECO_PLACA_H610);
            case "B760" -> new PlacaMaeIntel("Placa-mãe B760", PRECO_PLACA_B760);
            case "Z790" -> new PlacaMaeIntel("Placa-mãe Z790", PRECO_PLACA_Z790);
            default -> throw new IllegalArgumentException("Modelo de placa-mãe Intel desconhecido: " + modelo);
        };
    }
    @Override
    public List<String> getModelosProcessador() {
        return List.of("i3", "i5", "i7","i9");
    }
    @Override
    public List<String> getModelosPlacaMae() {
        return List.of("H610", "B760", "Z790");
    }
}