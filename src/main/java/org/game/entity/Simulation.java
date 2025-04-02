package org.game.entity;

import org.game.entity.animals.Creature;
import org.game.entity.animals.Herbivore;
import org.game.entity.animals.Predator;
import org.game.entity.immovableEntities.Grass;
import org.game.renderer.TerritoryConsoleRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Simulation {

    private TerritoryConsoleRenderer territoryConsoleRenderer;
    private Territory territory;
    private int MAX_SIMULATIONS; // Максимальное количество симуляций

    public Simulation(int maxSimulations) {
        this.MAX_SIMULATIONS = maxSimulations;
    }

    public void playSimulation() {
        System.out.println("Simulation started");
        boolean isNotFinished = true;
        int simulationCount = 0; // Счетчик симуляций

        while (isNotFinished) {
            createNewTerritory(); // Создание новой территории
            List<Entity> herbivoresAndPredators = territory.getEntitiesByType();

            // Если нет сущностей, завершаем симуляцию
            if (herbivoresAndPredators.isEmpty()) {
                isNotFinished = false;
                break; // Выход из цикла
            }

            // Проверка на количество сущностей
            long herbivoreCount = herbivoresAndPredators.stream()
                    .filter(entity -> entity.getSymbol().equals(Symbol.HERBIVORE))
                    .count();
            long predatorCount = herbivoresAndPredators.stream()
                    .filter(entity -> entity.getSymbol().equals(Symbol.PREDATOR))
                    .count();

            // Проверка на окончание симуляции
            if (herbivoreCount == 0 || predatorCount == 0 || simulationCount >= MAX_SIMULATIONS) {
                isNotFinished = false;
                break; // Выход из цикла
            }

            for (Entity entity : new ArrayList<>(herbivoresAndPredators)) {
                if (entity.getSymbol().equals(Symbol.HERBIVORE) || entity.getSymbol().equals(Symbol.PREDATOR)) {
                    Creature creature = (Creature) entity;
                    Set<Location> possibleCellsForMove = territory.getPossibleCellsForMove(); // Проверяем наличие свободных клеток для передвижения
                    if (!possibleCellsForMove.isEmpty()) {
                        Location location = possibleCellsForMove.iterator().next();
                        handleMovement(creature, location); // Перемещаем сущность в новую позицию
                    }
                }
            }

            try {
                Thread.sleep(1000); // Задержка между ходами
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            simulationCount++; // Увеличиваем счетчик симуляций
        }

        System.out.println("Simulation ended after " + simulationCount + " iterations.");
    }

    public void handleCreatureInteraction(Creature attacker, Creature target) {
        if (attacker.getSymbol().equals(Symbol.PREDATOR) && target.getSymbol().equals(Symbol.HERBIVORE)) {
            boolean attackResult = ((Predator) attacker).attack((Herbivore) target);
            if (attackResult) {
                // Убрать травоядное с карты
                removeEntity(target);
            }
        }
    }

    public void handleHerbivoreOnGrass(Herbivore herbivore, Grass grass) {
        if (grass.getGrassEnergy() > 0) {
            herbivore.eatGrass(grass);
            // Обновить отображение травы
            updateGrassVisual(grass);
        }
    }

    private void removeEntity(Entity entity) {
        territory.removeEntity(entity);
        territoryConsoleRenderer.render(territory); // Обновляем отображение карты
    }

    private void updateGrassVisual(Grass grass) {
        territory.removeEntity(grass);
        territory.addEntity(grass);
        territoryConsoleRenderer.render(territory); // Обновляем отображение карты
    }

    private void createNewTerritory() {
        territory = new Territory(); // Сохраняем созданную территорию в поле класса
        territory.setupDefaultEssencePosition();
        territoryConsoleRenderer = new TerritoryConsoleRenderer();
        territoryConsoleRenderer.render(territory); // Отображаем карту
    }

    public void handleMovement(Creature creature, Location newLocation) {
        Entity targetEntity = territory.getEntityAtLocation(newLocation);

        // Обрабатываем перемещение травоядного
        if (creature.getSymbol().equals(Symbol.HERBIVORE)) {
            Herbivore herbivore = (Herbivore) creature;
            if (targetEntity.getSymbol().equals(Symbol.GRASS)) {
                handleHerbivoreOnGrass(herbivore, (Grass) targetEntity);
            }
            territory.moveEntity(creature, newLocation);
        }
        // Обрабатываем атаку хищника
        else if (creature.getSymbol().equals(Symbol.PREDATOR)) {
            // Проверяем, есть ли цель
            if (targetEntity != null && targetEntity.getSymbol().equals(Symbol.HERBIVORE)) {
                handleCreatureInteraction((Predator) creature, (Herbivore) targetEntity);
            } else {
                territory.moveEntity(creature, newLocation);
            }
        }
    }

}





