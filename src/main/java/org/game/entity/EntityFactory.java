package org.game.entity;

public interface EntityFactory<T extends Entity>{
    Entity create(Symbol symbol, Location location);

}
