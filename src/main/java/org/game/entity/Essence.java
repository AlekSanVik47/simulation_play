package org.game.entity;

import java.util.HashSet;
import java.util.Set;

public abstract class Essence {
    public Location location;
    private final Symbol symbol;

    public Essence(Symbol symbol, Location location) {
        this.symbol = symbol;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Проверяем наличие пустого квадрата
     * @param essence проверяемая сущность
     * @param territory територия
     * @return если камень дерево или трава возвращаем false, иначе если квадрат пустой или содержит траву, возвращаем true, иначе false
     * @param <T> любая сущность
     */
    <T extends Essence> boolean isAvailableSquare (T essence, Territory territory) {
        return switch (essence.getSymbol()) {
            case ROCK, TREE, GRASS -> false;
            default -> territory.isSquareEmpty(essence) || territory.containsGrass(essence);
        };
    }

    Set<Location> isPossibleCellsForMove () {
        return new HashSet<>();
    }

}
