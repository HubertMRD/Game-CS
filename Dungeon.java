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
    private HashMap<String, NPC> npcs = new HashMap<>();

    public void addNPC(NPC npc) {
        npcs.put(npc.getName().toLowerCase(), npc);
    }

    public NPC getNPC(String name) {
        return npcs.get(name.toLowerCase());
    }

    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon();
        dungeon.buildWorld();
        dungeon.start();
    }

    public Wing buildWorld() {
       
        Item book = new Item("Book", "An old dusty book.");
        Item key = new Item("Key", "A small brass key.");
        Item torch = new Item("Torch", "A flickering torch.");
        Item notebook = new Item("NoteBook", "Something you can write in.");
        Item note = new Item("Note#1", "Find the other to find where you are.");
        Item note2 = new Item("Note#2", "If you find this note, good job, but there is no way out.");

      
        north.addItem(book);
        north.addItem(key);
        north.addItem(torch);
        north.addItem(notebook);
        east.addItem(note);
        north.addItem(note2);

       
        east.setLocked(true);

       
        north.addExit('s', south);
        south.addExit('n', north);
        north.addExit('e', east);
        east.addExit('w', north);
        west.addExit('e', north);

        currentWing = north;

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

            try {
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

                    case "talk":
                        if (!itemName.isEmpty()) {
                            NPC npc = currentWing.getNPC(itemName);
                            if (npc != null) {
                                npc.talk();
                            } else {
                                System.out.println("There is no one by that name to talk to.");
                            }
                        } else {
                            System.out.println("Specify who you'd like to talk to.");
                        }
                        break;

                    case "q":
                        System.out.println("Exiting the dungeon. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid command. Try again.");
                        break;
                }
            } catch (CantGoWayException e) {
                System.out.println(e.getMessage());
            }
        } while (!command.equals("q"));

        input.close();
    }

    public void moveTo(Wing newWing) {
        if (newWing == null) {
            throw new CantGoWayException("You can't move in that direction.");
        } else if (newWing.isLocked()) {
            if (getInventoryItem("key") != null) {
                newWing.setLocked(false);
                System.out.println("You used the key to unlock the " + newWing.getDescription() + " wing.");
                currentWing = newWing;
                System.out.println("You have moved to the " + currentWing.getDescription() + " wing.");
            } else {
                throw new CantGoWayException("The " + newWing.getDescription() + " wing is locked. You need a key.");
            }
        } else {
            currentWing = newWing;
            System.out.println("You have moved to the " + currentWing.getDescription() + " wing.");
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
        return inventory.get(itemName.toLowerCase());
    }

    public static void addToInventory(Item item) {
        if (item != null) {
            inventory.put(item.getName().toLowerCase(), item);
            System.out.println("Added " + item.getName() + " to inventory.");
        }
    }

    public class CantGoWayException extends RuntimeException {
        public CantGoWayException(String error) {
            super(error);
        }
    }
}

