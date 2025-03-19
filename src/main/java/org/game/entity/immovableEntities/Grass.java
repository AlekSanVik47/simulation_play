package org.game.entity.immovableEntities;

import org.game.entity.*;

import java.util.Set;

public class Grass extends Entity implements EntityFactory<Grass> {
    private int grassEnergy; //количество энергии травы

    public Grass(Symbol symbol, Location location) {
        super(symbol, location);
    }

    @Override
    protected Set<LocationTransitions> getEssenceTransitions() {
        return Set.of();
    }

    public int getGrassEnergy() {
        return grassEnergy;
    }

    public void setGrassEnergy(int grassEnergy) {
        this.grassEnergy = grassEnergy;
    }

    @Override
    public Grass create(Symbol symbol, Location location) {
        return new Grass(symbol, location);
    }
}
