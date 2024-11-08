package game;

import java.util.HashMap;
import java.util.Scanner;

public class Dungeon {
    private Wing north = new Wing("North");
    private Wing south = new Wing("South");
    private Wing east = new Wing("East");
    private Wing west = new Wing("West");
    private Wing currentWing;
    private static HashMap<String, Item> inventory = new HashMap<>();

    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon();
        dungeon.buildWorld();
        dungeon.start();
    }

    public Wing buildWorld() {
        Item book = new Item("Book", "An old dusty book.");
        Item key = new Item("Key", "A small brass key.");
        Item torch = new Item("Torch", "A flickering torch.");

        north.addItem(book);
        north.addItem(key);
        north.addItem(torch);

        currentWing = north;

        north.addExit('s', south);
        south.addExit('n', north);
        east.addExit('w', west);
        west.addExit('e', east);

        System.out.println("World has been built with items placed in the North wing.");
        return currentWing;
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Dungeon!");

        do {
            System.out.println("You are currently in the " + currentWing.getDescription() + " wing.");
            currentWing.listItems();
            System.out.print("What would you like to do? ");
            command = input.nextLine().toLowerCase();
            String[] words = command.split(" ");
            String action = words[0];
            String itemName = words.length > 1 ? words[1] : "";

            switch (action) {
                case "n":
                case "e":
                case "s":
                case "w":
                case "u":
                case "d":
                    moveTo(currentWing.getExit(action.charAt(0)));
                    break;

                case "take":
                    if (!itemName.isEmpty()) {
                        takeItem(itemName);
                    } else {
                        System.out.println("Specify an item to take.");
                    }
                    break;

                case "look":
                    if (!itemName.isEmpty()) {
                        lookAtItem(itemName);
                    } else {
                        System.out.println("Specify an item to look at.");
                    }
                    break;

                case "q":
                    System.out.println("Exiting the dungeon. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid command. Try again.");
                    break;
            }

        } while (!command.equals("q"));

        input.close();
    }

    public void moveTo(Wing newWing) {
        if (newWing != null) {
            currentWing = newWing;
            System.out.println("You have moved to the " + currentWing.getDescription() + " wing.");
        } else {
            System.out.println("You can't move in that direction.");
        }
    }

    private void takeItem(String itemName) {
        Item item = currentWing.getItem(itemName);
        if (item != null) {
            currentWing.removeItem(itemName);
            inventory.put(itemName, item);
            System.out.println("You take the " + itemName + ".");
        } else {
            System.out.println("There is no " + itemName + " here.");
        }
    }

    private void lookAtItem(String itemName) {
        Item item = getInventoryItem(itemName);
        if (item == null) {
            item = currentWing.getItem(itemName);
        }
        if (item != null) {
            System.out.println("You look at the " + itemName + ": " + item.getDescription());
        } else {
            System.out.println("There is no " + itemName + " here or in your inventory.");
        }
    }

    public static Item getInventoryItem(String itemName) {
        return inventory.get(itemName);
    }

    public static void addToInventory(Item item) {
        if (item != null) {
            inventory.put(item.getName().toLowerCase(), item);
            System.out.println("Added " + item.getName() + " to inventory.");
        }
    }
}


