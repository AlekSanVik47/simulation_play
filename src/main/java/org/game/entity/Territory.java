package org.game.entity;

import org.game.animals.Creature;
import org.game.animals.Herbivore;
import org.game.animals.Predator;

import java.util.HashMap;
import java.util.Random;

public class Territory {
    HashMap<Location, Essence> territoryMap = new HashMap<>();

    public void setLocation(Location location, Essence essence) {
        essence.location = location;
        territoryMap.put(location, essence);
    }
    public <T extends Essence> void forSetupDefaultPosition(Class<T> clazz, Symbol symbol) {
        Random random = new Random(10);
        int countEssence = random.nextInt(10);
        for (int i = 0; i < countEssence; i++) {
            Location location;
            do {
                location = new Location(random.nextInt(10), random.nextInt(10));
            }
            while (territoryMap.containsKey(location));
            try {
                T essence = clazz.getDeclaredConstructor(Symbol.class, Location.class).newInstance(symbol, location);
                territoryMap.put(location, essence);
                setLocation(location, essence);
                System.out.println(location.getX() + " " + location.getY() + " " + symbol.getSymbol());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countEssence = random.nextInt(10);
    }


    public void setupDefaultEssencePosition() {
        Random random = new Random(10);
        int countEssence = random.nextInt(10);

        // Добавляем камни
        for (int i = 0; i < countEssence; i++) {
            Location location;
            do {
                location = new Location(random.nextInt(10), random.nextInt(10));
            }
            while (territoryMap.containsKey(location));
                Rock rock = new Rock(Symbol.ROCK, location);
                territoryMap.put(location, rock);
                setLocation(location, rock);
                System.out.println(location.getX() + " " + location.getY() + " " + Symbol.ROCK.getSymbol());

        }
        countEssence = random.nextInt(10);

        //               Добавляем существ
        for (int j = 0; j < countEssence; j++) {
            Location location;
            do {
                location = new Location(random.nextInt(10), random.nextInt(10));
            }
            while (territoryMap.containsKey(location));
                Creature creature = new Creature(Symbol.CREATURE, new Location(random.nextInt(10), random.nextInt(10)));
                setLocation(location, creature);
                territoryMap.put(location, creature);
                System.out.println(location.getX() + " " + location.getY() + " " + Symbol.CREATURE.getSymbol());

        }
        countEssence = random.nextInt(15);

//               Добавляем деревья
        for (int k = 0; k < countEssence; k++) {
            Location location;
            do {
                location = new Location(random.nextInt(10), random.nextInt(10));
            }
            while (territoryMap.containsKey(location));
            Creature creature = new Creature(Symbol.TREE, new Location(random.nextInt(10), random.nextInt(10)));
            setLocation(location, creature);
            territoryMap.put(location, creature);
            System.out.println(location.getX() + " " + location.getY() + " " + Symbol.TREE.getSymbol());
        }


                }
    public static void main(String[] args) {
        Territory territory = new Territory();
        territory.setupDefaultEssencePosition();

        territory.forSetupDefaultPosition(Herbivore.class, Symbol.HERBIVORE);
        territory.forSetupDefaultPosition(Predator.class, Symbol.PREDATOR);
    }

            }



