package org.game;

import org.game.entity.Simulation;
import org.game.entity.Territory;
import org.game.renderer.TerritoryConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(10);
        simulation.playSimulation();
    }
}