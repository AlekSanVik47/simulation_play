package org.game.entity;

public enum Symbol {
    CREATURE('C'),
    HERBIVORE('H'),
    PREDATOR('P'),
    GRASS('G'),
    TREE('T'),
    ROCK('R');

    private final char symbol;
    Symbol(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }

}
