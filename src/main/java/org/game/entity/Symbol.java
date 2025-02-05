package org.game.entity;

public enum Symbol {
    CREATURE("🐉"), // Иконка для существа
    HERBIVORE("🐑"), // Иконка для травоядного
    PREDATOR("🐅"), // иконка для хищного
    GRASS("🌱"), // Иконка для травы
    TREE("🌳"), // Иконка для дерева
    ROCK("🪨"); // Иконка для камня


    private final String symbol;
    Symbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }

}
