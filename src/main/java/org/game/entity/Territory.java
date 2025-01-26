package org.game.entity;

import org.game.animals.Creature;
import org.game.animals.Herbivore;
import org.game.animals.Predator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Territory {
    HashMap<Location, Essence> territoryMap = new HashMap<>();
    private final Random random = new Random();
    private final EssenceFactory<Creature> factoryCreature = Creature::new; // создание существ
    private final EssenceFactory<Herbivore> factoryHerbivore = Herbivore::new; // создание травоядных существ
    private final EssenceFactory<Predator> factoryPredator = Predator::new; // создание хищных существ
    private final EssenceFactory<Rock> factoryRock = Rock::new; // создание камней
    private final EssenceFactory<Grass> factoryGrass = Grass::new; // создание травы (ресурсы)
    private final EssenceFactory<Tree> factoryTree = Tree::new; // создание деревьев

    public void setLocation(Location location, Essence essence) {
        essence.location = location;
        territoryMap.put(location, essence);
    }

    public <T extends Essence> void forSetupDefaultPosition(EssenceFactory<T> factory, Symbol symbol) {
        int MAX_COUNT = 10;
        int countEssence = random.nextInt(MAX_COUNT) + 1;
        for (int i = 0; i < countEssence; i++) {
            Location location;
            do {
                location = new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT));
            }
            while (territoryMap.containsKey(location));

            T essence = factory.create(symbol, location);
            territoryMap.put(location, essence);
            setLocation(location, essence);
      //      System.out.println(location.getX() + " " + location.getY() + " " + symbol.getSymbol());
        }
    }


    public void setupDefaultEssencePosition() {
        //               Добавляем камни
        forSetupDefaultPosition(factoryRock, Symbol.ROCK);
        //               Добавляем существ
        forSetupDefaultPosition(factoryCreature, Symbol.CREATURE);
        //               Добавляем деревья
        forSetupDefaultPosition(factoryGrass, Symbol.GRASS);
        //               Добавляем травы
        forSetupDefaultPosition(factoryTree, Symbol.TREE);
        //               Добавляем травоядных существ
        forSetupDefaultPosition(factoryHerbivore, Symbol.HERBIVORE);
        //               Добавляем хищных существ
        forSetupDefaultPosition(factoryPredator, Symbol.PREDATOR);


    }

    public Map<Location, Essence> getTerritoryMap() {
        return territoryMap;
    }

    public static void main(String[] args) {
        Territory territory = new Territory();
        territory.setupDefaultEssencePosition();

    }


}



