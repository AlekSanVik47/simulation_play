package org.game.entity;

import org.game.animals.Herbivore;
import org.game.animals.Predator;

import java.util.*;

public class Territory {
    private int MAX_COUNT = 10;
    HashMap<Location, Essence> territoryMap = new HashMap<>();
    private final Set<Location> occupiedLocations = new HashSet<>();

    public void addOccupiedLocation(Location location) {
        occupiedLocations.add(location);
    }


    private final Random random = new Random();
    private final Herbivore factoryHerbivore = new Herbivore(Symbol.HERBIVORE, new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT))); // создание травоядных существ
    private final Predator factoryPredator = new Predator(Symbol.PREDATOR, new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT))); // создание хищных существ
    private final Rock factoryRock = new Rock(Symbol.ROCK, new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT))); // создание камней
    private final Grass factoryGrass = new Grass(Symbol.GRASS, new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT))); // создание травы (ресурсы)
    private final Tree factoryTree = new Tree(Symbol.GRASS, new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT))); // создание деревьев

    public void setLocation(Location location, Essence essence) {
        essence.location = location;
        territoryMap.put(location, essence);
    }

    public <T extends Essence> void forSetupDefaultPosition(EssenceFactory<T> factory, Symbol symbol, Set<Location> occupiedLocations) {

        int countEssence = random.nextInt(MAX_COUNT) + 1;
        for (int i = 0; i < countEssence; i++) {
            Location location;
            do {
                location = new Location(random.nextInt(MAX_COUNT), random.nextInt(MAX_COUNT));
            }

            while (territoryMap.containsKey(location));
            T essence = factory.create(symbol, location);
            Set<Location> possibleCells = getPossibleCellsForMove(essence);
            for (Location loc : possibleCells) {
                if (loc.equals(location) && location.isFree(occupiedLocations)) {
                    territoryMap.put(loc, essence);
                    break;
                }

            }
            setLocation(location, essence);
        }
    }


    /**
     * @param essence проверяемая сущность
     * @param <T>     любая сущность
     * @return возвращаем true если квадрат пуст
     */
    public <T extends Essence> boolean isSquareEmpty(T essence) {
        return !territoryMap.containsKey(essence.getLocation());
    }

    /**
     * @param essence проверяемая сущность
     * @param <T>     любая сущность
     * @return возвращаем true если квадрат содержит траву
     */
    public <T extends Essence> boolean containsGrass(T essence) {
        return territoryMap.containsKey(essence.getLocation());
    }

    public void setupDefaultEssencePosition() {
        //               Добавляем камни
        forSetupDefaultPosition(factoryRock, Symbol.ROCK, occupiedLocations);
        //               Добавляем деревья
        forSetupDefaultPosition(factoryGrass, Symbol.GRASS, occupiedLocations);
        //               Добавляем травы
        forSetupDefaultPosition(factoryTree, Symbol.TREE, occupiedLocations);
        //               Добавляем травоядных существ
        forSetupDefaultPosition(factoryHerbivore, Symbol.HERBIVORE, occupiedLocations);
        //               Добавляем хищных существ
        forSetupDefaultPosition(factoryPredator, Symbol.PREDATOR, occupiedLocations);


    }

    public Map<Location, Essence> getTerritoryMap() {
        return territoryMap;
    }

    public int getSizeTerritory() {
        return territoryMap.size();
    }

    /**
     * Проверяем наличие пустого квадрата
     *
     * @param essence проверяемая сущность
     * @param <T>     любая сущность
     * @return если камень или дерево возвращаем false, иначе если квадрат пустой или содержит траву, возвращаем true, иначе false
     */
    <T extends Essence> boolean isAvailableSquare(T essence) {
        return switch (essence.getSymbol()) {
            case ROCK, TREE -> false;
            default -> isSquareEmpty(essence) || !containsGrass(essence);
        };
    }

    /**
     * Получаем возможные клетки для перемещения
     *
     * @param essence проверяемая сущность
     * @return possibleCells множество возможных клеток
     */
    public <T extends Essence> Set<Location> getPossibleCellsForMove(T essence) {
        Set<Location> possibleCells = new HashSet<>();
        for (LocationTransitions transitions : essence.getEssenceTransitions()) {
            if (essence.location.isTransitable(transitions)) {
                Location newLocation = essence.location.transition(transitions);
                if (isAvailableSquare(essence)) {
                    possibleCells.add(newLocation);
                }
            }

        }
        return possibleCells;
    }

}



