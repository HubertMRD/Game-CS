package game;

import java.io.Serializable;
import java.util.HashMap;

public class Wing implements Serializable {
    private static final long serialVersionUID = 1L; 
    private String description;
    private Wing east, west, north, south, up, down;
    private HashMap<String, Item> items;
    private boolean locked;

    
    public static HashMap<String, Wing> allWings = new HashMap<>();

    public Wing(String description) {
        this.description = description;
        this.items = new HashMap<>();
        this.locked = false;
        allWings.put(description.toLowerCase(), this); 
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public Item getItem(String itemName) {
        return items.get(itemName.toLowerCase());
    }

    public void removeItem(String itemName) {
        if (items.remove(itemName.toLowerCase()) == null) {
            System.out.println("Item '" + itemName + "' not found, so it could not be removed.");
        }
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("No items in this wing.");
        } else {
            System.out.println("Items in " + description + ":");
            for (String itemName : items.keySet()) {
                System.out.println("- " + itemName);
            }
        }
    }

    public Wing getExit(char direction) {
        switch (direction) {
            case 'e': return east;
            case 'w': return west;
            case 'n': return north;
            case 's': return south;
            case 'u': return up;
            case 'o': return down;
            default: return null;
        }
    }

    public void addExit(char direction, Wing room) {
        switch (direction) {
            case 'e': east = room; break;
            case 'w': west = room; break;
            case 'n': north = room; break;
            case 's': south = room; break;
            case 'u': up = room; break;
            case 'o': down = room; break;
            default: System.out.println("Invalid direction: " + direction);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
