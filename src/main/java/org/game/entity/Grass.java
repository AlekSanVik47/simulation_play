package org.game.entity;

public class Grass extends Essence {
    private  int grassEnergy; //количество энергии травы

    public Grass(Symbol symbol, Location location) {
        super(symbol, location);
    }
    public int getGrassEnergy() {
        return grassEnergy;
    }

    public void setGrassEnergy(int grassEnergy) {
        this.grassEnergy = grassEnergy;
    }

}
