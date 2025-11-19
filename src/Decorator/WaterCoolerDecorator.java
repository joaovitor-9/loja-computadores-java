package Decorator;

import AbstractFactory.ComponenteInterface;

public class WaterCoolerDecorator extends UpgradeDecoratorAbstract {
    public WaterCoolerDecorator(ComponenteInterface computadorBase) {
        super(computadorBase);
    }
    @Override
    public String getNome() {
        return computadorDecorado.getNome() + " + Water Cooler Premium";
    }
    @Override
    public double getPreco() {
        return computadorDecorado.getPreco() + 450.00;
    }
    @Override
    public String getDescricao() {
        return computadorDecorado.getDescricao() + "\t- (Upgrade) Water Cooler (R$ 450.0)\n";
    }
}