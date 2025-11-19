package Decorator;

import AbstractFactory.ComponenteInterface;

public class KitFanDecorator extends UpgradeDecoratorAbstract {
    public KitFanDecorator(ComponenteInterface computadorBase) {
        super(computadorBase);
    }
    @Override
    public String getNome() {
        return computadorDecorado.getNome() + " + Kit de fans";
    }
    @Override
    public double getPreco() {
        return computadorDecorado.getPreco() + 49.00;
    }
    @Override
    public String getDescricao() {
        return computadorDecorado.getDescricao() + "\t- (Upgrade) Kit de fans (R$ 49.99)\n";
    }
}