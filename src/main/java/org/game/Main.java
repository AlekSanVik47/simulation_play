package org.game;

import org.game.entity.Territory;
import org.game.renderer.TerritoryConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        Territory territory = new Territory();
        TerritoryConsoleRenderer renderer = new TerritoryConsoleRenderer();
        renderer.render(territory);
    }
}