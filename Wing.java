package game;

import java.util.HashMap;

public class Wing {
	private String description;
	private Wing east, west, north, south, up, down;
	private HashMap<String, Item> items;

	public Wing(String d) {
		description = d;
		this.items = new HashMap<>();
	}

	
	public void addItem(Item item) {
		items.put(item.getName(), item); 
	}


	public Item getItem(String itemName) {
		if (items.containsKey(itemName)) {
			return items.get(itemName);
		} else {
			System.out.println("Item '" + itemName + "' not found in " + description);
			return null;
		}
	}

	// Removes an item by name
	public void removeItem(String itemName) {
		if (items.remove(itemName) == null) {
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
			default: break;
		}
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

	    