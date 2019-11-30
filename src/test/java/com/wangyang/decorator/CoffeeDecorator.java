package com.wangyang.decorator;

/**
 * 咖啡的"装饰器"，可以给咖啡添加各种"配料"
 */
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee coffee) {
        decoratedCoffee = coffee;
    }
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }

    @Override
    public String getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}
