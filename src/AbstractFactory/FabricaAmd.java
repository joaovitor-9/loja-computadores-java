package AbstractFactory;

import java.util.List;

public class FabricaAmd implements FabricaDeComponentesInterface {
    public static final double PRECO_RYZEN_3 = 1100.0;
    public static final double PRECO_RYZEN_5 = 1700.0;
    public static final double PRECO_RYZEN_7 = 2800.0;
    public static final double PRECO_RYZEN_9 = 4200.0;

    public static final double PRECO_PLACA_A620 = 1000.0;
    public static final double PRECO_PLACA_B650 = 1600.0;
    public static final double PRECO_PLACA_X670 = 3500.0;

    @Override
    public ProcessadorInterface criarProcessador(String modelo) {
        return switch (modelo) {
            case "Ryzen 3" -> new ProcessadorAmd("AMD Ryzen 3 8300G", PRECO_RYZEN_3);
            case "Ryzen 5" -> new ProcessadorAmd("AMD Ryzen 5 7600X", PRECO_RYZEN_5);
            case "Ryzen 7" -> new ProcessadorAmd("AMD Ryzen 7 7700X", PRECO_RYZEN_7);
            case "Ryzen 9" -> new ProcessadorAmd("AMD Ryzen 9 7900X", PRECO_RYZEN_9);
            default -> throw new IllegalArgumentException("Modelo de processador AMD desconhecido: " + modelo);
        };
    }
    @Override
    public PlacaMaeInterface criarPlacaMae(String modelo) {
        return switch (modelo) {
            case "A620" -> new PlacaMaeAmd("Placa-mãe A620", PRECO_PLACA_A620);
            case "B650" -> new PlacaMaeAmd("Placa-mãe B650", PRECO_PLACA_B650);
            case "X670" -> new PlacaMaeAmd("Placa-mãe X670", PRECO_PLACA_X670);
            default -> throw new IllegalArgumentException("Modelo de placa-mãe AMD desconhecido: " + modelo);
        };
    }

    @Override
    public List<String> getModelosProcessador() {
        return List.of("Ryzen 3", "Ryzen 5", "Ryzen 7", "Ryzen 9");
    }

    @Override
    public List<String> getModelosPlacaMae() {
        return List.of("A620", "B650", "X670");
    }
}