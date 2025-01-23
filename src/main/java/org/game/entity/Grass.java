package org.game.entity;

public class Grass extends Essence {
    private  int grassEnergy; //количество энергии травы

    public Grass(Location location, char symbol) {
        super(location, symbol);
    }

    public int getGrassEnergy() {
        return grassEnergy;
    }

    public void setGrassEnergy(int grassEnergy) {
        this.grassEnergy = grassEnergy;
    }

}
