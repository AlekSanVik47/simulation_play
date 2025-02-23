package org.game.entity;

public enum Symbol {

    HERBIVORE("🐑"), // Иконка для травоядного
    PREDATOR("🐅"), // иконка для хищного
    GRASS("🌱"), // Иконка для травы
    TREE("🌳"), // Иконка для дерева
    ROCK("🪨_"); // Иконка для камня


    private final String symbol;
    Symbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

}
