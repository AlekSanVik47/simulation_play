package org.game.entity.animals;

import org.game.entity.Location;
import org.game.entity.LocationTransitions;
import org.game.entity.Territory;

import java.util.HashSet;
import java.util.Set;

public interface MovingAroundTerritory {

    Territory territory = new Territory();

    default Set<LocationTransitions> getTransitions() {
        Set<LocationTransitions> transitions = new HashSet<>();
        // Получаем возможные клетки для движения
        Set<Location> possibleCells = territory.getPossibleCellsForMove();
        for (Location location : possibleCells) {
            for (int i = -1; i <= 1; i++) { // Изменяем шаг на 1, чтобы не выходить за пределы
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue; // Пропускаем нулевое смещение
                    // Создаем новый переход из текущей клетки
                    int newX = location.getX() + i;
                    int newY = location.getY() + j;
                    Location newLocation = new Location(newX, newY);
                    // Проверяем, находится ли новый Location в пределах возможных клеток
                    if (possibleCells.contains(newLocation)) {
                        transitions.add(new LocationTransitions(newX, newY));
                    }
                }
            }
        }
        return transitions;
    }
}
