package org.game.entity;

import org.game.entity.animals.Creature;
import org.game.entity.animals.Herbivore;
import org.game.entity.animals.Predator;
import org.game.entity.immovableEntities.Grass;
import org.game.entity.immovableEntities.Rock;
import org.game.entity.immovableEntities.Tree;

import java.util.*;

public class Territory implements Actions{
    private int MAX_COUNT = 10;
    HashMap<Location, Entity> territoryMap = new HashMap<>();
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

    public void setLocation(Location location, Entity entity) {
        entity.location = location;
        territoryMap.put(location, entity);
    }

    /**
     *  Размещаем сущности на карте
     * @param factory фабрика для создания сущностей
     * @param symbol символ сущности
     * @param occupiedLocations занятые клетки
     * @param <T> сущность для дефолтного размещения
     */
    public <T extends Entity> void forSetupDefaultPosition(EntityFactory<T> factory, Symbol symbol, Set<Location> occupiedLocations) {
        int countEssence = random.nextInt(MAX_COUNT) + 1;
        Set<Location> possibleCells = new HashSet<>();
        // Генерируем все возможные позиции
        for (int x = 0; x < MAX_COUNT; x++) {
            for (int y = 0; y < MAX_COUNT; y++) {
                Location loc = new Location(x, y);
                if (!territoryMap.containsKey(loc) && loc.isFree(occupiedLocations)) {
                    possibleCells.add(loc);
                }
            }
        }
        // Перемешиваем доступные позиции для случайного выбора
        List<Location> shuffledCells = new ArrayList<>(possibleCells);
        Collections.shuffle(shuffledCells);
        for (int i = 0; i < Math.min(countEssence, shuffledCells.size()); i++) {
            Location location = shuffledCells.get(i);
            Entity entity = factory.create(symbol, location);
            for (Location loc : possibleCells) {
                if (loc.equals(location) && location.isFree(occupiedLocations)) {
                    territoryMap.put(loc, entity);
                    break;
                }
        }
            setLocation(location, entity);
        }
    }


    /**
     * @return возвращаем true если квадрат пуст
     */
    public boolean isSquareEmpty(Entity entity) {
        return !territoryMap.containsKey(entity.getLocation());
    }

    /**
     * @param grass трава
     * @return возвращаем true если квадрат содержит траву
     */
    public boolean isContainsGrass(Grass grass) {
        return territoryMap.containsKey(grass.getLocation());
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

    public Map<Location, Entity> getTerritoryMap() {
        return territoryMap;
    }

    public int getSizeTerritory() {
        return territoryMap.size();
    }

    /**
     * Проверяем наличие пустого квадрата
     *
     * @return если камень или дерево возвращаем false, иначе если квадрат пустой или содержит траву, возвращаем true, иначе false
     */
    public boolean isAvailableSquare(Location location) {
        Entity entity = territoryMap.get(location);
        if (entity == null) {
            return true;
        }
            switch (entity.getSymbol()) {
                case ROCK, TREE -> {
                    return false;
                }
                case GRASS -> {
                    return true;
                }
                default -> {
                    if (isSquareEmpty(entity)) {
                        return true;
                    }
                }
            }
        return false;
    }

    /**
     * Получаем возможные клетки для перемещения
     *
     * @param creature проверяемая сущность
     * @return possibleCells множество возможных клеток
     */
    public Set<Location> getPossibleCellsForMove(Creature creature) {
        Set<Location> possibleCells = new HashSet<>();
        for (LocationTransitions transitions : creature.getEssenceTransitions()) {
            if (creature.location.isTransitable(transitions)) {
                Location newLocation = creature.location.transition(transitions);
                if (isAvailableSquare(newLocation)) {
                    possibleCells.add(newLocation);
                }
            }

        }
        return possibleCells;
    }

    /**
     * Получаем возможные клетки для размещения
     * @param entity сущность
     * @return получаем множество для размещения
     */
    public Set<Location> getPlacementEntities (Entity entity) {
        Set<Location> possibleCells = new HashSet<>();
        for (Location location : territoryMap.keySet()) {
            if(entity.getSymbol().equals(Symbol.HERBIVORE) || entity.getSymbol().equals(Symbol.PREDATOR)) {
                if (isAvailableSquare(location)) {
                    possibleCells.add(location);
                }
            } else {
                if (isSquareEmpty(entity)){
                    possibleCells.add(location);
                }
            }
        }
        return possibleCells;
    }

    @Override
    public void initActions() {
        setupDefaultEssencePosition();
    }

    @Override
    public void turnActions() {

    }
}



