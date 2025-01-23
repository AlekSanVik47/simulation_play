package org.game.entity;

import java.util.HashMap;

public class Territory {
    HashMap<Location, Essence> territoryMap = new HashMap<>();

    public void setLocation(Location location, Essence world) {
        world.location = location;
        territoryMap.put(location, world);
    }

   public void setupDefaultPosition(){
        // set rock
       setLocation(new Location(5,5), new Rock(new Location(5,5), 'R'));
       setLocation(new Location(1,1), new Rock(new Location(1,1), 'R'));
       setLocation(new Location(7,2), new Rock(new Location(7,2), 'R'));

       //set tree
       setLocation(new Location(1,2), new Tree(new Location(1,2), 'T'));
       setLocation(new Location(9,7), new Tree(new Location(9,7), 'T'));
       setLocation(new Location(5,0), new Tree(new Location(5,0), 'T'));

   }
}
