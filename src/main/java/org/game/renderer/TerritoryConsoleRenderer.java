package org.game.renderer;

import org.game.entity.Essence;
import org.game.entity.Location;
import org.game.entity.Territory;

import java.util.HashMap;
import java.util.Map;

public class TerritoryConsoleRenderer {
    private static final String DEFAULT = " | _ ";

    public void render(Territory territory) {
        print(territory.getTerritoryMap());
    }

    public void print(Map<Location, Essence> territoryMap) {
        System.out.print("       "); // Отступ для номера столбца
        for (int i = 0; i < 10; i++) { // Печатаем номера столбцов
            System.out.print((i + 1) + "    "); // Добавляем пробелы для выравнивания
        }
        System.out.println();

        for (int y = 0; y < 10; y++) { // Печатаем строки
            // Добавляем пробелы для выравнивания номеров строк
            if (y < 9) {
                System.out.print((y + 1) + "   "); // Номера строк от 1 до 9
            } else {
                System.out.print((y + 1) + "  "); // Номер строки 10
            }
            for (int x = 0; x < 10; x++) {
                Location location = new Location(x, y);
                Essence essence = territoryMap.get(location);
                if (essence != null) {
                    System.out.print(" | " + essence.getSymbol().getSymbol() + " "); // Печатаем символ сущности
                } else {
                    System.out.print(DEFAULT); // Печатаем пустую ячейку
                }
            }
            System.out.println(); // Переход на новую строку после завершения строки
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Territory territory = new Territory();
        territory.setupDefaultEssencePosition();

        TerritoryConsoleRenderer renderer = new TerritoryConsoleRenderer();
        renderer.render(territory); // Отображаем карту
    }
}

