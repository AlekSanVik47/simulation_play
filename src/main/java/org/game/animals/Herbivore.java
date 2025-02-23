package org.game.animals;

import org.game.entity.EssenceFactory;
import org.game.entity.Location;
import org.game.entity.Symbol;

public class Herbivore extends Creature implements EssenceFactory<Herbivore> {
   private final int STEPS_HERBIVORE = 3;
    public Herbivore(Symbol symbol, Location location) {
        super(symbol, location);
        this.countsSteps = STEPS_HERBIVORE; // Устанавливаем количество шагов {
    }

    @Override
    public void makeMove(int countSteps) {

    }


    public int getCountsSteps() {
        return countsSteps;
    }

    @Override
    public Herbivore create(Symbol symbol, Location location) {
        return new Herbivore(symbol, location);
    }
}
