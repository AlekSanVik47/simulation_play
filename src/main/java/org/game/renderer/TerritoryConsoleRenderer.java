package org.game.renderer;

import org.game.entity.Territory;

public class TerritoryConsoleRenderer {
    public void render(Territory territory) {
        for (int rank = 10; rank>=1; rank--){
            for (int i = 1; i < 10; i++) {
                System.out.println(rank + " " + i);
            }
        }
    }
}
