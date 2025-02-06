package org.game.renderer;

import org.game.entity.Essence;
import org.game.entity.Location;
import org.game.entity.Territory;

import java.util.Map;

public class TerritoryConsoleRenderer {
    private static final String DEFAULT = "|_____";

    public void render(Territory territory) {
        print(territory.getTerritoryMap());
    }

    public void print(Map<Location, Essence> territoryMap) {
        // Печатаем заголовок с номерами столбцов
        System.out.print("      "); // Отступ для номера столбца
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-6d", i); // Форматируем номера столбцов
        }
        System.out.println();

        for (int y = 0; y < 10; y++) {
            // Печатаем номер строки с отступами
            System.out.printf("%-3d", (y + 1)); // Номера строк от 1 до 10

            for (int x = 0; x < 10; x++) {
                Location location = new Location(x, y);
                Essence essence = territoryMap.get(location);

                if (essence != null) {
                    // Печатаем ячейку с сущностью
                    System.out.print("|__" + essence.getSymbol().getSymbol() + "_"); // Символ сущности
                } else {
                    // Печатаем пустую ячейку
                    System.out.print(DEFAULT);
                }
            }
            System.out.println(); // Переход на новую строку после завершения строки

        }
        System.out.println(); // Дополнительная пустая строка в конце
    }


    public static void main(String[] args) {
        Territory territory = new Territory();
        territory.setupDefaultEssencePosition();

        TerritoryConsoleRenderer renderer = new TerritoryConsoleRenderer();
        renderer.render(territory); // Отображаем карту
    }
}

