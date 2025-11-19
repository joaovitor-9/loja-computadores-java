package Decorator;

import AbstractFactory.ComponenteInterface;

public class RGBDecorator extends UpgradeDecoratorAbstract {
    public RGBDecorator(ComponenteInterface computadorBase) {
        super(computadorBase);
    }
    @Override
    public String getNome() {
        return computadorDecorado.getNome() + " + Kit de Iluminação RGB";
    }
    @Override
    public double getPreco() {
        return computadorDecorado.getPreco() + 200.00;
    }
    @Override
    public String getDescricao() {
        return computadorDecorado.getDescricao() + "\t- (Upgrade) Kit de Iluminação RGB (R$ 200.0)\n";
    }
}