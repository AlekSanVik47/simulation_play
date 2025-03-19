package org.game.entity.animals;

import org.game.entity.EntityFactory;
import org.game.entity.Location;
import org.game.entity.Symbol;

public class Predator extends Creature implements EntityFactory<Predator> {
    private final int STEPS_PREDATOR = 5;
    public Predator(Symbol symbol, Location location) {
        super(symbol, location);
        this.countsSteps = STEPS_PREDATOR; // Устанавливаем количество шагов)) {
    }

    @Override
    public void makeMove(int countSteps) {

    }

    @Override
    public Predator create(Symbol symbol, Location location) {
        return new Predator(symbol, location);
    }
}
