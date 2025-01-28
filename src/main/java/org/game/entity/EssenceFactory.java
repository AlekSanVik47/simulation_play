package org.game.entity;

public interface EssenceFactory  <T extends Essence>{
    T create(Symbol symbol, Location location);

}
