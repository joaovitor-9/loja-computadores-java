package Decorator;

import AbstractFactory.ComponenteInterface;

public abstract class UpgradeDecoratorAbstract implements ComponenteInterface {
    protected ComponenteInterface computadorDecorado;
    public abstract String getNome();
    public abstract double getPreco();
    public abstract String getDescricao();

    public UpgradeDecoratorAbstract(ComponenteInterface computadorBase) {
        this.computadorDecorado = computadorBase;
    }

}